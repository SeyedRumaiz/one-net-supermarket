package com.example.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AddItemController extends SceneManager {

    @FXML
    private TextField itemThresholdQuantity;

    @FXML
    private BorderPane addBorderPane;

    @FXML
    private TextField itemCodeField;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemBrandField;

    @FXML
    private TextField itemPriceField;

    @FXML
    private TextField itemQuantityField;

    @FXML
    private TextField itemCategoryField;

    @FXML
    private DatePicker itemDateField;

    @FXML
    private Button itemImagePathField;

    @FXML
    private Label addCodeValidation;

    @FXML
    private Label addNameValidation;

    @FXML
    private Label addBrandValidation;

    @FXML
    private Label addPriceValidation;

    @FXML
    private Label addQuantityValidation;

    @FXML
    private Label addCategoryValidation;

    @FXML
    private Label addDateValidation;

    @FXML
    private Label addThresholdQuantityValidation;

    @FXML
    private Label addImageValidation;

    private boolean allValid = true;

    @FXML
    void mainMenuPopUp(ActionEvent event) {
        openScene(addBorderPane, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    void deleteItemPopUp(ActionEvent event) {
        openScene(addBorderPane, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    void updateItemPopUp(ActionEvent event) {
        openScene(addBorderPane, "UpdateItem.fxml", "Update Item");
    }
    @FXML
    void viewItemsPopUp(ActionEvent event) {
        openScene(addBorderPane, "ViewItems.fxml", "View Items");
    }
    @FXML
    void selectRandomDealersPopUp(ActionEvent event) {
        openScene(addBorderPane, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    void viewDealerDetailsPopUp(ActionEvent event) {
        openScene(addBorderPane, "ViewDealerDetails.fxml", "View Dealer Details");
    }
    @FXML
    private void viewDealerItemsPopUp(ActionEvent event) {
        openScene(addBorderPane, "ViewDealerItems.fxml", "View Dealer Items");
    }
    @FXML
    private void exitApp(ActionEvent event) {
        openScene(addBorderPane, "Exit.fxml", "Exit");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(addBorderPane, "AddItem.fxml", "Add Item");
    }

    @FXML
    /// code handling
    public int handleCode() {
        int code = Validation.handleCode(itemCodeField, addCodeValidation);
        if (code == -1) {
            allValid = false;
        }
        return code;
    }
    @FXML
    /// name handling
    private String handleName() {
        String name =  Validation.handleInput(itemNameField, addNameValidation);
        if (name.isEmpty()) {
            allValid = false;
        }
        return name;
    }
    @FXML
    /// brand handling
    private String handleBrand() {
        String brand =  Validation.handleInput(itemBrandField, addBrandValidation);
        if (brand.isEmpty()) {
            allValid = false;
        }
        return brand;
    }
    @FXML
    /// price handling
    private double handlePrice() {
        double price = Validation.handlePrice(itemPriceField, addPriceValidation);
        if (price == -1) {
            allValid = false;
        }
        return price;
    }
    @FXML
    /// quantity handling
    private short handleQuantity() {
        short quantity =  Validation.handleQuantity(itemQuantityField, addQuantityValidation);
        if (quantity == -1) {
            allValid = false;
        }
        return quantity;
    }
    @FXML
    /// category handling
    private String handleCategory() {
        String category = Validation.handleInput(itemCategoryField, addCategoryValidation);
        if (category.isEmpty()) {
            allValid = false;
        }
        return category;
    }

    @FXML
    /// threshold quantity handling
    private byte handleThresholdQuantity() {
        byte threshold = Validation.handleThresholdQuantity(itemThresholdQuantity, addThresholdQuantityValidation);
        if (threshold == -1) {
            allValid = false;
        }
        return threshold;
    }

    @FXML
    /// date handling
    private String handleDate() {
        String date = Validation.handleDate(itemDateField, addDateValidation);
        if (date.isEmpty()) {
            allValid = false;
        }
        return date;
    }

    @FXML
    /// for the actual image input
    private String handleImagePath() {
        return Validation.handleImagePath((Stage) addBorderPane.getScene().getWindow(), itemImagePathField, addImageValidation);
    }
    /// for the image path being stored, this method prevents the file choosing window opening again
    private String handleImagePathField() {
        if (itemImagePathField.getText().equals("Select Image")) {
            allValid = false;               // if the user skips the image input
            return "";
        }
        return itemImagePathField.getText();        // default image, so an image path is always there
    }
    @FXML
    ///  This method saves details
    public void onClickSaveDetails() {
        int code = handleCode();
        String name = handleName();
        String brand = handleBrand();
        double price = handlePrice();
        short quantity = handleQuantity();
        String category = handleCategory();
        byte thresholdQuantity = handleThresholdQuantity();
        String date = handleDate();
        String imagePath = handleImagePathField();

        if (allValid) {
            boolean confirming =  showAlert("Do you wish to save?", "Do you want to make the recent changes?", AlertType.CONFIRMATION);
            if (confirming) {
                ItemFileHandler itemFileHandler = new ItemFileHandler();
                itemFileHandler.loadItems();
                // add the new item when the user confirms
                itemFileHandler.addItem(code, name, brand, price, quantity, category, date, imagePath, thresholdQuantity);
                showAlert("Success", "Item has been added!", AlertType.INFORMATION);
            }
            else {
                showAlert("Error", "Item has not been added!", AlertType.ERROR);
            }
        }
        else {
            showAlert("Failure", "Please fill all fields correctly!", AlertType.ERROR);
            allValid = true;            // reset when user enters a correct input
        }
    }

    /// This method is used to display key alerts to the user for data changes
    private boolean showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        if (alert.showAndWait().get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}
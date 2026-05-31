package com.example.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UpdateItemPostController extends SceneManager {

    @FXML
    private MenuBar updateMenuBar;

    @FXML
    private BorderPane updateBorderPane;

    @FXML
    private TextField updateItemNameField;

    @FXML
    private TextField updateItemBrandField;

    @FXML
    private TextField updateItemPriceField;

    @FXML
    private TextField updateItemQuantityField;

    @FXML
    private TextField updateItemCategoryField;

    @FXML
    private Button updateItemImagePathField;

    @FXML
    private Label updateNameValidation;

    @FXML
    private Label updateBrandValidation;
    @FXML
    private Label updatePriceValidation;
    @FXML
    private Label updateQuantityValidation;
    @FXML
    private Label updateCategoryValidation;
    @FXML
    private Label updateImageValidation;
    @FXML
    private Button updateConfirmButton;
    @FXML
    private Label updateSuccessful;

    private int oldItemCode;
    private boolean allValid = true;

    /// Method to receive the code of the item being updated
    public void setTransferringCode(int oldCode) {
        this.oldItemCode = oldCode;
    }

    @FXML
    private void mainMenuPopUp(ActionEvent event) {
        openScene(updateBorderPane, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(updateBorderPane, "AddItem.fxml", "Add Item");
    }
    @FXML
    private void deleteItemPopUp(ActionEvent event) {
        openScene(updateBorderPane, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    private void viewItemsPopUp(ActionEvent event) {
        openScene(updateBorderPane, "ViewItems.fxml", "View Items");
    }
    @FXML
    private void selectRandomDealersPopUp(ActionEvent event) {
        openScene(updateBorderPane, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    private void viewDealerDetailsPopUp(ActionEvent event) {
        openScene(updateBorderPane, "ViewDealerDetails.fxml", "View Dealer Details");
    }
    @FXML
    private void viewDealerItemsPopUp(ActionEvent event) {
        openScene(updateBorderPane, "ViewDealerItems.fxml", "View Dealer Items");
    }
    @FXML
    private void exitApp(ActionEvent event) {
        openScene(updateBorderPane, "Exit.fxml", "Exit");
    }
    @FXML
    private void updateItemPopUp(ActionEvent event) {
        openScene(updateBorderPane, "UpdateItem.fxml", "Update Item");
    }

    @FXML
    /// name handling
    private String handleName() {
        String name= Validation.handleInput(updateItemNameField, updateNameValidation);
        if (name.isEmpty()) {
            allValid = false;
        }
        return name;
    }
    @FXML
    /// brand handling
    private String handleBrand() {
        String brand =  Validation.handleInput(updateItemBrandField, updateBrandValidation);
        if (brand.isEmpty()) {
            allValid = false;
        }
        return brand;
    }
    @FXML
    /// price handling
    private double handlePrice() {
        double price = Validation.handlePrice(updateItemPriceField, updatePriceValidation);
        if (price == -1) {
            allValid = false;
        }
        return price;
    }
    @FXML
    /// quantity handling
    private short handleQuantity() {
        short quantity = Validation.handleQuantity(updateItemQuantityField, updateQuantityValidation);
        if (quantity == -1) {
            allValid = false;
        }
        return quantity;
    }
    @FXML
    /// category handling
    private String handleCategory() {
        String category = Validation.handleInput(updateItemCategoryField, updateCategoryValidation);
        if (category.isEmpty()) {
            allValid = false;
        }
        return category;
    }

    @FXML
    /// image handling
    private String handleImagePath() {
        return Validation.handleImagePath((Stage) updateBorderPane.getScene().getWindow(), updateItemImagePathField, updateImageValidation);
    }

    /// image path handling
    private String handleImage() {
        if (updateItemImagePathField.getText().equals("Select Image")) {        // if the user skips this input
            allValid = false;
        }
        return updateItemImagePathField.getText();      // the path needed to store
    }
    @FXML
    ///  saving the details
    public void onClickSaveDetails() {
        String itemName = handleName();
        String itemBrand = handleBrand();
        double itemPrice = handlePrice();
        short itemQuantity = handleQuantity();
        String itemCategory = handleCategory();
        String itemImagePath = handleImage();

        if (allValid) {
            boolean confirming =  showAlert("Do you wish to save?", "Do you want to make the recent changes?", Alert.AlertType.CONFIRMATION);
            if (confirming) {
                ItemFileHandler itemFileHandler = new ItemFileHandler();
                itemFileHandler.loadItems();
                itemFileHandler.updateItem(oldItemCode, itemName, itemBrand, itemPrice, itemQuantity, itemCategory, itemImagePath);
                showAlert("Success", "New details saved!", Alert.AlertType.INFORMATION);
            }
            else {
                showAlert("Error", "New details were unsaved!", Alert.AlertType.ERROR);
            }
        }
        else {
            showAlert("Failure", "Please fill all fields correctly!", Alert.AlertType.ERROR);
            allValid = true;
        }
    }

    /// get confirmation from the user and display errors
    private boolean showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        if (alert.showAndWait().get() == ButtonType.OK) {
            return true;
        }
        else {
            return false;
        }
    }
}
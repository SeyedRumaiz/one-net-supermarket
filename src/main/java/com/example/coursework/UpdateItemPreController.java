package com.example.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class UpdateItemPreController extends SceneManager{
    @FXML
    private TextField updatingField;

    @FXML
    private Label updatingLabel;

    @FXML
    private BorderPane preBorderpane;

    @FXML
    Label errorLabel;

    @FXML
    /// validates the text-field input and passes the code to the post scene
    public void validate() {
        String code = updatingField.getText().replace(" ", "");
        String error = errorValidation(code);

        errorLabel.setText(error);
        if (error.isEmpty()) {
            try {
                errorLabel.setText(error);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateItemPost.fxml"));
                Parent root = loader.load();
                UpdateItemPostController controller = loader.getController();   // going to pass the code between controllers
                controller.setTransferringCode(Integer.parseInt(code));
                preBorderpane.getScene().setRoot(root);
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    /// Helper method validating the item code
    public String errorValidation(String input) {
        if (input.isEmpty()) {
            return "Please enter a code to update!";
        }
        int code;
        try {
            ItemFileHandler itemFileHandler = new ItemFileHandler();
            itemFileHandler.loadItems();
            code = Integer.parseInt(input);
            if (!itemFileHandler.codeExists(code)) {
                return "This item does not exist!";
            }
        } catch (NumberFormatException e) {
            return "Please enter a numeric item code!";
        }
        return "";
    }

    @FXML
    private void mainMenuPopUp(ActionEvent event) {
        openScene(preBorderpane, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    private void deleteItemPopUp(ActionEvent event) {
        openScene(preBorderpane, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    private void viewItemPopUp(ActionEvent event) {
        openScene(preBorderpane, "ViewItems.fxml", "View Items");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(preBorderpane, "AddItem.fxml", "Add Item");
    }
    @FXML
    private void selectRandomDealers(ActionEvent event) {
        openScene(preBorderpane, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    private void viewDealerDetailsPopUp(ActionEvent event) {
        openScene(preBorderpane, "ViewDealerDetails.fxml", "View Dealer Details");
    }
    @FXML
    private void viewDealerItemsPopUp(ActionEvent event) {
        openScene(preBorderpane, "ViewDealerItems.fxml", "View Dealer Items");
    }
    @FXML
    private void updateItemPopUp(ActionEvent event) {
        openScene(preBorderpane, "UpdateItem.fxml", "Update Item");
    }
    @FXML
    private void exitApp(ActionEvent event) {
        openScene(preBorderpane, "Exit.fxml", "Exit");
    }
}
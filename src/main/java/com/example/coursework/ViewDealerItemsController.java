package com.example.coursework;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class ViewDealerItemsController extends SceneManager {

    private ObservableList<Dealer> dealerList;

    @FXML
    private Label randomDealerSuccessMessage;

    @FXML
    private BorderPane preRandomDealerItems;

    @FXML
    private TextField randomDealerTextField;

    @FXML
    /// This method is used to get the random dealers, from select random dealers and view dealer details
    public void randomDealerInput(ObservableList<Dealer> randomDealerList) {
        this.dealerList = randomDealerList;
    }

    @FXML
    /// This method is used to validate the dealer name input entered by the user
    public void handleSelectDealer(ActionEvent event) {
        String dealerName = randomDealerTextField.getText();

        String error = dealerValidator(dealerName);
        randomDealerSuccessMessage.setText(error);

        if (error.isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDealerItemsPost.fxml"));
                // user is directed to the items offered by this dealer
                Parent root = loader.load();

                ViewDealerItemsPostController controller = loader.getController();

                controller.initData(dealerList, dealerName);
                // pass the dealer's name and random dealers list in order to display the items

                Stage stage = (Stage) preRandomDealerItems.getScene().getWindow();
                Scene scene = new Scene(root, 1440, 1240);
                scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/styles.css")).toExternalForm());

                String formattedDealerName = dealerName.substring(0,1).toUpperCase() + dealerName.substring(1);
                                                    // capitalize
                stage.setScene(scene);
                stage.setTitle("Items offered by " + formattedDealerName);
                stage.show();
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    /// Helper method for dealer's name validation
    public String dealerValidator(String dealerName) {
        if (dealerList == null) {       // if the user did not select 4 random dealers
            return "You have not selected any random dealers!";
        }

        boolean isPresent = false;
        for (Dealer dealer : dealerList) {
            if (dealer.getName().equalsIgnoreCase(dealerName)) {
                isPresent = true;       // checking if the random dealer entered is matching
                break;
            }
        }
        if (!isPresent) {
            return "Please choose one of the random dealers!";
        }
        return "";
    }

    @FXML
    private void exitApp(ActionEvent event) {
        openScene(preRandomDealerItems, "Exit.fxml", "Exit");
    }
    @FXML
    private void mainMenuPopUp(ActionEvent event) {
        openScene(preRandomDealerItems, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(preRandomDealerItems, "AddItem.fxml", "Add Item");
    }
    @FXML
    private void deleteItemPopUp(ActionEvent event) {
        openScene(preRandomDealerItems, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    private void updateItemPopUp(ActionEvent event) {
        openScene(preRandomDealerItems, "UpdateItem.fxml", "Update Item");
    }
    @FXML
    private void viewItemsPopUp(ActionEvent event) {
        openScene(preRandomDealerItems, "ViewItems.fxml", "View Items");
    }
    @FXML
    private void selectRandomDealersPopUp(ActionEvent event) {
        openScene(preRandomDealerItems, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    private void viewDealerDetailsPopUp(ActionEvent event) {
        openScene(preRandomDealerItems, "ViewDealerDetails.fxml", "View Dealer Details");
    }
    @FXML
    private void viewDealerItemsPopUp(ActionEvent event) {
        openScene(preRandomDealerItems, "ViewDealerItems.fxml", "View Dealer Items");
    }
}
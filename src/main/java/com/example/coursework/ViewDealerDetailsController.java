package com.example.coursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class ViewDealerDetailsController extends SceneManager {

    private ObservableList<Dealer> randomDealerList;

    @FXML
    private GridPane dealerDetailsGridPane;

    @FXML
    private BorderPane viewDealerBorderPane;

    /// This method receives the chosen four dealers via the observable dealers list from select random dealers controller
    public void setDealerList(ObservableList<Dealer> dealerList) {
        this.randomDealerList = dealerList;
        populateDealerDetailsGrid();
    }

    /// This method populates all the data into a grid format
    public void populateDealerDetailsGrid() {

        int row = 1;        // the headers are put into the 0th row

        for (Dealer dealer : randomDealerList) {

            // Adding now dealer's info with rowspan of 3
            dealerDetailsGridPane.add(new Label(dealer.getName()), 0, row-1, 1, 3);
            dealerDetailsGridPane.add(new Label(dealer.getPhoneNumber()), 1, row-1, 1, 3);
            dealerDetailsGridPane.add(new Label(dealer.getLocation()), 2, row-1, 1, 3);

            // now add the 3 rows of dealer items
            ObservableList<DealerItem> items = FXCollections.observableArrayList(dealer.getItems());
            for (int i = 0; i < items.size(); i++) {
                DealerItem item = items.get(i);

                dealerDetailsGridPane.add(new Label(item.getDealerItemName()), 3, row+i);
                dealerDetailsGridPane.add(new Label(item.getDealerItemBrand()), 4, row+i);
                dealerDetailsGridPane.add(new Label("" +item.getDealerItemPrice()), 5, row+i);
                dealerDetailsGridPane.add(new Label("" + item.getDealerItemQuantity()), 6, row+i);
            }
            row += 3;       // each dealer has 3 rows of items
        }
    }

    @FXML
    private void mainMenuPopUp(ActionEvent event) {
        openScene(viewDealerBorderPane, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(viewDealerBorderPane, "AddItem.fxml", "Add Item");
    }
    @FXML
    private void deleteItemPopUp(ActionEvent event) {
        openScene(viewDealerBorderPane, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    private void updateItemPopUp(ActionEvent event) {
        openScene(viewDealerBorderPane, "UpdateItem.fxml", "Update Item");
    }
    @FXML
    private void viewItemsPopUp(ActionEvent event) {
       openScene(viewDealerBorderPane, "ViewItems.fxml", "View Items");
    }

    @FXML
    private void selectDealersPopUp() {
        openScene(viewDealerBorderPane, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    private void viewDealerDetailsPopUp() {
        openScene(viewDealerBorderPane, "ViewDealerDetails.fxml", "View Dealer Details");
    }
    @FXML
    private void viewDealerItemButtonClicked(ActionEvent event) {
        viewDealerItemsPopUp(randomDealerList);  // this list is passed to the next page
    }
    @FXML
    private void exitApp(ActionEvent event) {
        openScene(viewDealerBorderPane, "ExitApp.fxml", "Exit");
    }

    @FXML
    /// This method passes the selected random dealers list to the dealer items page
    private void viewDealerItemsPopUp(ObservableList<Dealer> dealerList) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDealerItems.fxml"));
            Parent root = loader.load();

            // get controller

            ViewDealerItemsController controller = loader.getController();

            // now we pass the dealers list
            controller.randomDealerInput(dealerList);

            // now show the scene
            Stage stage = (Stage) viewDealerBorderPane.getScene().getWindow();
            Scene scene = new Scene(root, 1440, 1240);
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/styles.css")).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
package com.example.coursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ViewDealerItemsPostController extends SceneManager {

    @FXML
    private BorderPane viewDealerItemsPostBorderPane;

    private ObservableList<Dealer> dealerList;

    private String selectedName;

    @FXML
    private TableColumn<DealerItem, String> selectedDealerItemName;

    @FXML
    private TableColumn<DealerItem, String> selectedDealerItemBrand;

    @FXML
    private TableColumn<DealerItem, Double> selectedDealerItemPrice;

    @FXML
    private TableColumn<DealerItem, Short> selectedDealerItemQuantity;

    @FXML
    private TableView<DealerItem> selectedDealerItemsTable;

    /// This method receives the random dealer list along with the name entered by the user
    public void initData(ObservableList<Dealer> dealerList, String selectedName) {
        this.dealerList = dealerList;
        this.selectedName = selectedName;
        populateTable();
    }

    /// This method populates the tableview with the selected dealer's items
    private void populateTable() {
        ObservableList<DealerItem> filteredItems = FXCollections.observableArrayList();

        for (Dealer dealer : dealerList) {
            if (dealer.getName().equalsIgnoreCase(selectedName)) {      // checking if the
                                                                    // user's name matches
                filteredItems.addAll(dealer.getItems());
                break;
            }
        }
        selectedDealerItemsTable.setItems(filteredItems);
    }

    @FXML
    public void initialize() {
        selectedDealerItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        selectedDealerItemName.setCellValueFactory(new PropertyValueFactory<>("dealerItemName"));
        selectedDealerItemBrand.setCellValueFactory(new PropertyValueFactory<>("dealerItemBrand"));
        selectedDealerItemPrice.setCellValueFactory(new PropertyValueFactory<>("dealerItemPrice"));
        selectedDealerItemQuantity.setCellValueFactory(new PropertyValueFactory<>("dealerItemQuantity"));
    }

    @FXML
    private void mainMenuPopUp(ActionEvent event) {
        openScene(viewDealerItemsPostBorderPane, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(viewDealerItemsPostBorderPane, "AddItem.fxml", "Add Item");
    }
    @FXML
    private void deleteItemPopUp(ActionEvent event) {
        openScene(viewDealerItemsPostBorderPane, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    private void updateItemPopUp(ActionEvent event) {
        openScene(viewDealerItemsPostBorderPane, "UpdateItem.fxml", "Update Item");
    }
    @FXML
    private void viewItemsPopUp(ActionEvent event) {
        openScene(viewDealerItemsPostBorderPane, "ViewItems.fxml", "View Items");
    }
    @FXML
    private void selectRandomDealersPopUp(ActionEvent event) {
        openScene(viewDealerItemsPostBorderPane, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    private void viewDealerDetailsPopUp(ActionEvent event) {
        openScene(viewDealerItemsPostBorderPane, "ViewDealerDetails.fxml", "View Dealer Details");
    }
    @FXML
    private void viewDealerItemsPopUp(ActionEvent event) {
        openScene(viewDealerItemsPostBorderPane, "ViewDealerItems.fxml", "View Dealer Items");
    }
    @FXML
    private void exitApp(ActionEvent event) {
        openScene(viewDealerItemsPostBorderPane, "Exit.fxml", "Exit");
    }
}

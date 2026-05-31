package com.example.coursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MainMenuController extends SceneManager implements Initializable {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    TableView<Item> table;

    @FXML
    TableColumn<Item, Integer> itemCode;

    @FXML
    TableColumn<Item, String> itemName;

    @FXML
    TableColumn<Item, String> itemBrand;

    @FXML
    TableColumn<Item, Short> itemQuantity;

    @FXML
    TableColumn<Item, ImageView> itemPath;

    @FXML
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemBrand.setCellValueFactory(new PropertyValueFactory<>("itemBrand"));
        itemQuantity.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        itemPath.setCellValueFactory(new PropertyValueFactory<>("itemImageView"));
        loadLowStockItems();
    }
    private void loadLowStockItems() {
        ItemFileHandler itemFileHandler = new ItemFileHandler();
        itemFileHandler.loadItems();
        ObservableList<Item> lowStockItems = FXCollections.observableArrayList(itemFileHandler.lowStockItems());
        table.setItems(lowStockItems);
    }

    @FXML
    private void mainMenuPopUp(ActionEvent event) {
        openScene(mainBorderPane, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(mainBorderPane, "AddItem.fxml", "Add Item");
    }
    @FXML
    private void deleteItemPopUp(ActionEvent event)  {
        openScene(mainBorderPane, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    private void viewItemsPopUp(ActionEvent event)  {
        openScene(mainBorderPane, "ViewItems.fxml", "View Items");
    }
    @FXML
    private void selectRandomDealersPopUp(ActionEvent event) {
        openScene(mainBorderPane, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    private void viewDealerDetailsPopUp(ActionEvent event) {
        openScene(mainBorderPane, "ViewDealerDetails.fxml", "View Dealer Details");
    }
    @FXML
    private void updateItemPopUp(ActionEvent event) {
        openScene(mainBorderPane, "UpdateItem.fxml", "Update Item");
    }
    @FXML
    private void viewDealerItemsPopUp(ActionEvent event) {
        openScene(mainBorderPane, "ViewDealerItems.fxml", "View Dealer Items");
    }
    @FXML
    private void exitApp(ActionEvent event) {
        openScene(mainBorderPane, "Exit.fxml", "Exit");
    }
}
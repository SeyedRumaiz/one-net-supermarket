package com.example.coursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ViewItemsController extends SceneManager implements Initializable {
    @FXML
    private MenuBar viewMenuBar;

    @FXML
    private BorderPane viewItemsBorderPane;

    @FXML
    private Label totalItemCount;

    @FXML
    private Label totalInventoryValue;

    @FXML
    private TableView<Item> viewTable;

    @FXML
    private TableColumn<Item, String> viewCategory;

    @FXML
    private TableColumn<Item, Integer> viewCode;

    @FXML
    private TableColumn<Item, String> viewName;

    @FXML
    private TableColumn<Item, String> viewBrand;

    @FXML
    private TableColumn<Item, Double> viewPrice;

    @FXML
    private TableColumn<Item, Short> viewQuantity;

    @FXML
    private TableColumn<Item, String> viewPurchasedDate;

    @FXML
    private TableColumn<Item, ImageView> viewImage;

    @FXML
    /// Initialize the table view set up
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        viewTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // to allow column resizing
        viewCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        viewCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        viewName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        viewBrand.setCellValueFactory(new PropertyValueFactory<>("itemBrand"));
        viewPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        viewQuantity.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        viewPurchasedDate.setCellValueFactory(new PropertyValueFactory<>("itemPurchasedDate"));
        viewImage.setCellValueFactory(new PropertyValueFactory<>("itemImageView"));
        loadSortedItems();
    }

    /// Method to load the sorted items
    private void loadSortedItems() {
        ItemFileHandler itemFileHandler = new ItemFileHandler();
        itemFileHandler.loadItems();
        ObservableList<Item> sortedItems = FXCollections.observableArrayList(itemFileHandler.viewItems());
        viewTable.setItems(sortedItems);
        totalItemCount.setText(String.valueOf(itemFileHandler.totalItems));
        totalInventoryValue.setText("Rs. " + itemFileHandler.formattedInventoryValue);
    }

    @FXML
    private void mainMenuPopUp(ActionEvent event) {
        openScene(viewItemsBorderPane, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(viewItemsBorderPane, "AddItem.fxml", "Add Item");
    }
    @FXML
    private void deleteItemPopUp(ActionEvent event) {
        openScene(viewItemsBorderPane, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    private void updateItemPopUp(ActionEvent event) {
        openScene(viewItemsBorderPane, "UpdateItem.fxml", "Update Item");
    }
    @FXML
    private void viewItemsPopUp(ActionEvent event) {
        openScene(viewItemsBorderPane, "ViewItems.fxml", "View Items");
    }
    @FXML
    private void selectRandomDealersPopUp(ActionEvent event) {
        openScene(viewItemsBorderPane, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    private void viewDealerDetailsPopUp(ActionEvent event) {
        openScene(viewItemsBorderPane, "ViewDealerDetails.fxml", "View Dealer Details");
    }
    @FXML
    private void viewDealerItemsPopUp(ActionEvent event) {
        openScene(viewItemsBorderPane, "ViewDealerItems.fxml", "View Dealer Items");
    }
    @FXML
    private void exitApp(ActionEvent event) {
        openScene(viewItemsBorderPane, "Exit.fxml", "Exit");
    }
}
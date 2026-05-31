package com.example.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteItemController extends SceneManager implements Initializable {

    @FXML
    private BorderPane deleteBorderPane;

    @FXML
    ListView<String> deletingList;

    @FXML
    private TextField deletingSearchBar;

    List<Item> itemDetailsList = new ArrayList<>();

    @FXML
    ///  This method remove a row by selecting the row and clicking the remove button
    public void removeRowOnClick(ActionEvent event) {

        String selectedItem = deletingList.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;     // when an item is not selected
        }
        String selectedCode = selectedItem.split(" ")[0];

        Item toRemove = null;
        for (Item item : itemDetailsList) {
            if (String.valueOf(item.getItemCode()).equals(selectedCode)) {
                // finding the item
                toRemove = item;
                break;
            }
        }
        if (toRemove != null) {
            boolean saving = showAlert("You are about to delete that item!", "Would you like to save the changes?", Alert.AlertType.CONFIRMATION);

            if (saving) {
                ItemFileHandler itemFileHandler = new ItemFileHandler();
                itemFileHandler.loadItems();
                itemDetailsList.remove(toRemove);
                itemFileHandler.deleteItem(toRemove);           // remove completely
                deletingList.getItems().remove(selectedItem);   // item won't be shown anymore on the list
            } else {
                showAlert("Failure to save changes", "Item was not deleted!", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    /// Method which is used to filter the results
    public void search(ActionEvent event) {
        String query = deletingSearchBar.getText().trim();

        List<String> filteredDisplay = itemDetailsList.stream().filter(
                        item -> (item.getItemCode() + "").contains(query)       // filter by item code
                )
                .map(item -> String.format("%-30s %-30s %-30s %-30.2f %-30d", item.getItemCode(), item.getItemName(), item.getItemBrand(), item.getItemPrice(), item.getItemQuantity())).collect(Collectors.toList());       // fields that get shown from filtering
                                            // convert the result to formatted strings
        deletingList.getItems().clear();                    // clear the old list
        deletingList.getItems().addAll(filteredDisplay);        // updated list is shown
    }

    @Override
    /// Initial list of items displayed to the user
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        ItemFileHandler itemFileHandler = new ItemFileHandler();
        itemDetailsList = itemFileHandler.loadItems();

        List<String> displayList = itemDetailsList.stream().map(item -> String.format("%-30s %-30s %-30s %-30.2f %-30d",
                item.getItemCode(), item.getItemName(), item.getItemBrand(), item.getItemPrice(), item.getItemQuantity())).collect(Collectors.toList());
        deletingList.getItems().addAll(displayList);        // initial list, maps each items
    }

    /// confirmation for saving details
    public boolean showAlert(String title, String message, Alert.AlertType type) {

            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            if (alert.showAndWait().get() == ButtonType.OK) {
                return true;
            } else {
                return false;
            }
    }

    @FXML
    private void mainMenuPopUp(ActionEvent event) {
        openScene(deleteBorderPane, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(deleteBorderPane, "AddItem.fxml", "Add Item");
    }
    @FXML
    private void deleteItemPopUp(ActionEvent event) {
        openScene(deleteBorderPane, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    private void viewItemsPopUp(ActionEvent event) {
        openScene(deleteBorderPane, "ViewItems.fxml", "View Items");
    }
    @FXML
    private void updateItemPopUp(ActionEvent event) {
        openScene(deleteBorderPane, "UpdateItem.fxml", "Update Item");
    }
    @FXML
    private void selectRandomDealersPopUp(ActionEvent event) {
        openScene(deleteBorderPane, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    private void viewDealerDetailsPopUp(ActionEvent event) {
        openScene(deleteBorderPane, "ViewDealerDetails.fxml", "View Dealer Details");
    }
    @FXML
    private void viewDealerItemsPopUp(ActionEvent event) {
        openScene(deleteBorderPane, "ViewDealerItems.fxml", "View Dealer Items");
    }
    @FXML
    private void exitApp(ActionEvent event) {
        openScene(deleteBorderPane, "Exit.fxml", "Exit");
    }
}
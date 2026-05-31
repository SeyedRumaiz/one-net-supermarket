package com.example.coursework;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SelectRandomDealersController extends SceneManager implements Initializable {

    private ObservableList<Dealer> randomDealers;

    private ObservableList<Dealer> sortedObservableDealers;

    @FXML
    private BorderPane selectRandomBorderPane;

    @FXML
    private TableView<Dealer> randomTable;

    @FXML
    private TableColumn<Dealer, String> randomDealerName;

    @FXML
    private TableColumn<Dealer, String> randomDealerNumber;

    @FXML
    private TableColumn<Dealer, String> randomDealerLocation;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        randomTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        randomDealerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        randomDealerNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        randomDealerLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    @FXML
    /// This method selects the four random dealers
    public void OnClickSelectRandomDealers(ActionEvent event) throws IOException {
        DealersFileHandler dealersFileHandler = new DealersFileHandler();
        dealersFileHandler.loadDealers();
        List<Dealer> pickedDealers = dealersFileHandler.selectRandomDealers();
        // In order to check for the dealer's items in the dealers items controller

        randomDealers = FXCollections.observableArrayList(pickedDealers);
        randomTable.setItems(randomDealers);

        List<Dealer> sortedDealers = dealersFileHandler.randomDealerDetails(pickedDealers);
        sortedObservableDealers = FXCollections.observableArrayList(sortedDealers);
        // The ones picked are sorted
    }

    @FXML
    /// This method is used to pass the selected dealers to the next dealers details page
    public void viewDealerDetailsPopUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDealerDetails.fxml"));
        Parent root = loader.load();
        if (sortedObservableDealers == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Random Dealers Selection Error");
            alert.setHeaderText(null);
            alert.setContentText("You have not selected any dealers!");
            alert.showAndWait();
            return;
        }
        ViewDealerDetailsController controller = loader.getController();
        controller.setDealerList(sortedObservableDealers);      // passing this list to the next controller
        selectRandomBorderPane.getScene().setRoot(root);
    }

    @FXML
    /// This method passes the selected dealers to view the items offered by one of them
    public void viewDealerItemsPopUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDealerItems.fxml"));
        Parent root = loader.load();

        // passing this list to the dealer items controller to get the dealer's items
        ViewDealerItemsController controller = loader.getController();
        controller.randomDealerInput(sortedObservableDealers);
        selectRandomBorderPane.getScene().setRoot(root);
    }

    @FXML
    private void mainMenuPopUp(ActionEvent event) {
        openScene(selectRandomBorderPane, "MainMenu.fxml", "Main Menu");
    }
    @FXML
    private void addItemPopUp(ActionEvent event) {
        openScene(selectRandomBorderPane, "AddItem.fxml", "Add Item");
    }
    @FXML
    private void deleteItemPopUp(ActionEvent event) {
        openScene(selectRandomBorderPane, "DeleteItem.fxml", "Delete Item");
    }
    @FXML
    private void updateItemPopUp(ActionEvent event) {
        openScene(selectRandomBorderPane, "UpdateItem.fxml", "Update Item");
    }
    @FXML
    private void viewItemsPopUp(ActionEvent event) {
        openScene(selectRandomBorderPane, "ViewItems.fxml", "View Items");
    }
    @FXML
    private void selectRandomDealersPopUp(ActionEvent event) {
        openScene(selectRandomBorderPane, "SelectRandomDealers.fxml", "Select Random Dealers");
    }
    @FXML
    private void exitApp(ActionEvent event) {
        openScene(selectRandomBorderPane, "Exit.fxml", "Exit");
    }
}
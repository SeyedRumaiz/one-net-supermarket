package com.example.coursework;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class ExitController extends SceneManager {

    @FXML
    private BorderPane exitBorderPane;

    @FXML
    private void handleYes(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    private void handleNo(ActionEvent event) {
        openScene(exitBorderPane, "MainMenu.fxml", "Main Menu");
            // goes to the menu when canceled
    }
}
package com.example.coursework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public abstract class SceneManager {

    ///  Method used by subclasses to switch scenes
    protected void openScene(Node node, String fxmlFile, String title) {
        Stage stage = (Stage) node.getScene().getWindow();
        switchScene(stage, fxmlFile, title);
    }

    ///  Method for every scene switching to take place
    private void switchScene(Stage stage, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1440, 1240);
            stage.setScene(scene);
            scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/styles.css"), "CSS file was not found.").toExternalForm());
                                                                                            // Null pointer exception if the css file is not found
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
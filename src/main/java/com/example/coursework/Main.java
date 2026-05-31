package com.example.coursework;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1440, 1240);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/styles.css")).toExternalForm());
        ItemFileHandler itemFileHandler = new ItemFileHandler();
        DealersFileHandler dealersFileHandler = new DealersFileHandler();
        itemFileHandler.loadItems();
        dealersFileHandler.loadDealers();
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
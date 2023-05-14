package com.example.hoi4modder;

import com.example.hoi4modder.controller.MainController;
import com.example.hoi4modder.service.Destinations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class to create main window and start application
 */
public class ModApplication extends Application {
    /**
     *
     * @param stage - main state
     * @throws IOException if main view was not found
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ModApplication.class.getResource("controller/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Hoi4 modification");
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(400);
        stage.setResizable(true);

        javafx.scene.image.Image icon = new javafx.scene.image.Image(Destinations.get().icon());
        stage.getIcons().add(icon);

        stage.show();
        MainController controller = fxmlLoader.getController();
        controller.start();
    }
    public static void main(String[] args) {
        launch();
    }
}
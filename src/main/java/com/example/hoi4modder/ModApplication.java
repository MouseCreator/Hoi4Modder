package com.example.hoi4modder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ModApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ModApplication.class.getResource("controller/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Hoi4 modification");
        stage.setScene(scene);
        stage.show();

        stage.setMinWidth(800);
        stage.setMinHeight(400);
        stage.setResizable(true);
    }

    public static void main(String[] args) {
        launch();
    }
}
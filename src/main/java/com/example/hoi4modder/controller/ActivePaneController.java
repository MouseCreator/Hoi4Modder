package com.example.hoi4modder.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class ActivePaneController {
    private Pane content;
    private MainController parentController;

    public boolean onCloseRequest() {
        return true;
    }

    public void onClose() {
    }
    public void initContent() {
    }

    public ActivePaneController load(MainController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(parentController.getClass().getResource(getFilename()));
        try {
            Pane pane = fxmlLoader.load();
            ActivePaneController controller = fxmlLoader.getController();
            controller.content = pane;
            controller.parentController = parentController;
            return controller;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFilename() {
        return "country-editor.fxml";
    }

    public Pane getContent() {
        return content;
    }
}

package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.common.Country;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Class for controllers in active workspace
 */
public abstract class ActivePaneController {
    private Pane content;
    protected MainController parentController;

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

    protected abstract String getFilename();

    public Pane getContent() {
        return content;
    }
    public abstract void load();

    public abstract Country getCountry();
}

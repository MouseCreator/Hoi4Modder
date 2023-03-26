package com.example.hoi4modder.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import java.net.URL;
import java.util.ResourceBundle;

public class CountryEditorController extends ActivePaneController implements Initializable {

    @FXML
    private ImageView flagImage;

    @FXML
    void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
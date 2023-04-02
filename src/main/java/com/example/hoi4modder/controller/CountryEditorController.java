package com.example.hoi4modder.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CountryEditorController extends ActivePaneController implements Initializable {

    @FXML
    private ImageView flagImage;

    @FXML
    private ImageView communismFlag;

    @FXML
    private ImageView democraticFlag;

    @FXML
    private ImageView fascismFlag;
    @FXML
    private ImageView imageDDS;
    @FXML
    private ImageView neutralityFlag;
    @FXML
    void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void handleDragDrop(DragEvent event) throws FileNotFoundException {
        List<File> filesReceived = event.getDragboard().getFiles();
        handleFileFist(filesReceived);
    }
    private void handleFileFist(List<File> files) throws FileNotFoundException {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @Override
    protected String getFilename() {
        return "country-editor.fxml";
    }

    @Override
    public void save() {
        
    }

    @Override
    public void load() {

    }

    @FXML
    void loadImage(ActionEvent event) {

    }
}
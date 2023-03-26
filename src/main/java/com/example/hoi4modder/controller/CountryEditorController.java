package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.Country;
import com.example.hoi4modder.game.Ideology;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CountryEditorController extends ActivePaneController implements Initializable {

    @FXML
    private ImageView flagImage;

    private Country country = new Country();

    @FXML
    private ImageView communismFlag;

    @FXML
    private ImageView democraticFlag;

    @FXML
    private ImageView fascismFlag;

    @FXML
    private ImageView neutralityFlag;

    Map<Ideology, Image> flags = new HashMap<>();
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
        for (File file : files) {
            if (file.getName().contains("communism")) {
                Image image = new Image(new FileInputStream(file));
                flags.put(Ideology.communism, image);
                communismFlag.setImage(image);
            } else if (file.getName().contains("democratic")) {
                Image image = new Image(new FileInputStream(file));
                flags.put(Ideology.democratic, image);
                democraticFlag.setImage(image);
            } else if (file.getName().contains("fascism")) {
                Image image = new Image(new FileInputStream(file));
                flags.put(Ideology.fascism, image);
                fascismFlag.setImage(image);
            } else if (file.getName().contains("neutrality")) {
                Image image = new Image(new FileInputStream(file));
                flags.put(Ideology.neutrality, image);
                neutralityFlag.setImage(image);
            } else {
                parentController.report("Flags filename has to contain ideology specification!");
            }
        }
        flagImage.setImage(flags.get(Ideology.communism));
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
}
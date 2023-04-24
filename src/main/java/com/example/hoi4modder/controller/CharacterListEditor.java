package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.multithreading.LoadingTask;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.strategy.PutReplaceStrategy;
import com.example.hoi4modder.service.Destinations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

/**
 * Controller for character list
 */
public class CharacterListEditor extends ActivePaneController implements Initializable {

    @FXML
    private ListView<Pane> charactersListView;
    private final List<CharacterItemController> controllerList = new ArrayList<>();
    private String countryTag;

    private GameCharacterList characters = GameCharacterList.getArrayList();
    @FXML
    private TextField tagTextField;
    @Override
    protected String getFilename() {
        return "character-list.fxml";
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @FXML
    public void duplicateSelected() {
        System.out.println("Duplicated");
    }

    @FXML
    public void addEmptyCharacter() {
        System.out.println("Added character");
    }
    @FXML
    public void removeSelected() {
        System.out.println("Removed character");
    }

    @FXML
    public void loadCharactersByTag() {
        this.countryTag = tagTextField.getText();
        loadListFromFile();
    }
    private String getFileToLoad(String tag) {
        FileSearchService searcher = parentController.getSavedData().fileSearchService();
        searcher.setStrategy(new PutReplaceStrategy());
        searcher.setDirectory(Destinations.get().characters());
        return searcher.findCountryByTag(tag);
    }
    private void loadListFromFile() {
        if (tagTextField.getText().isEmpty()) {
            GameCharacterList.getArrayList();
            return;
        }
        String tag = tagTextField.getText().toUpperCase();
        try {
            String filename = getFileToLoad(tag);
            loadFromThread(filename);
        } catch (NoSuchElementException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Cannot find country with tag " + tag, ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void loadFromThread(String filename) {
        Thread thread = new Thread(new LoadingTask(this, filename, characters, charactersListView, controllerList));
        thread.setName("CharacterLoadingThread");
        thread.start();
    }

    public String getCountryTag() {
        return countryTag;
    }

    public void setCountryTag(String countryTag) {
        this.countryTag = countryTag;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        charactersListView.setFocusTraversable( false );
    }
}


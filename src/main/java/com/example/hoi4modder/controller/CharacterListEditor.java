package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.strategy.PutReplaceStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.NoSuchElementException;

public class CharacterListEditor extends ActivePaneController {

    @FXML
    private ListView<?> charactersListView;

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

    private GameCharacterList characters;

    @FXML
    public void loadCharactersByTag() {
        if (tagTextField.getText().isEmpty())
            return;
        String tag = tagTextField.getText().toUpperCase();
        try {
            FileSearchService searcher = (FileSearchService) parentController.getObjectPool().get("filesearcher");
            searcher.setStrategy(new PutReplaceStrategy());
            searcher.setDirectory("common/characters");
            String filename = searcher.findCountryByTag(tag);
            //this.characters =
        } catch (NoSuchElementException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Cannot find country with tag " + tag, ButtonType.OK);
            alert.showAndWait();
        }

    }
}


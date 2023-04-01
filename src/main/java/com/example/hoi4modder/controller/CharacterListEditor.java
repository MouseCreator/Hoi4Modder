package com.example.hoi4modder.controller;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.strategy.PutReplaceStrategy;
import com.example.hoi4modder.service.AbstractFactory;
import com.example.hoi4modder.service.Destinations;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
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
        characters = loadListFromFile();
        System.out.println(characters);
    }
    private GameCharacterList loadListFromFile() {
        if (tagTextField.getText().isEmpty())
            return GameCharacterList.getArrayList();
        String tag = tagTextField.getText().toUpperCase();
        try {
            String filename = getFilename(tag);
            return AbstractFactory.get().getCharacterList(filename);
        } catch (NoSuchElementException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Cannot find country with tag " + tag, ButtonType.OK);
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error occured during reading a file!", ButtonType.OK);
            alert.showAndWait();
        }
        return GameCharacterList.getArrayList();
    }

    private String getFilename(String tag) {
        FileSearchService searcher = (FileSearchService) parentController.getObjectPool().get("filesearcher");
        searcher.setStrategy(new PutReplaceStrategy());
        searcher.setDirectory(Destinations.get().characters());
        return searcher.findCountryByTag(tag);
    }
}


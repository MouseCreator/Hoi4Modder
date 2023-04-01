package com.example.hoi4modder.controller;

import com.example.hoi4modder.service.Destinations;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
}


package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.controller.character_extra.GameCharacterCreator;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class EditorListTask extends Task<Void> {
    protected GameCharacterList characters;
    protected CharacterListEditor editor;
    protected ObservableList<Pane> paneList = FXCollections.observableArrayList();
    protected List<CharacterItemController> controllerList = new ArrayList<>();

    protected void createListOfCharacters() {
        GameCharacterCreator creator = new GameCharacterCreator(editor, paneList, controllerList);
        for (GameCharacter character : characters) {
            creator.addItem(character);
        }
    }
    public List<CharacterItemController> getControllers() {
        return controllerList;
    }

    public ObservableList<Pane> getPanes() {
        return paneList;
    }
}

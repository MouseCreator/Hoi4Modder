package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.utilities.Strings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchingTask extends Task<Void> {

    private final CharacterListEditor editor;
    private final String targetString;
    private final ObservableList<Pane> panes = FXCollections.observableArrayList();

    private final GameCharacterList characters;
    private final GameCharacterList initialCharacters;
    private final List<CharacterItemController> controllers = new ArrayList<>();

    public SearchingTask(CharacterListEditor editor, String target, GameCharacterList initialCharacters) {
        this.editor = editor;
        this.targetString = target;
        this.initialCharacters = initialCharacters;
        characters = GameCharacterList.getArrayList();
    }

    private void createListOfCharacters() {
        for (GameCharacter character : characters) {
            FXMLLoader itemLoader = new FXMLLoader();
            itemLoader.setLocation(editor.getClass().getResource("character-item.fxml"));
            try {
                Pane pane = itemLoader.load();
                CharacterItemController controller = itemLoader.getController();
                controller.setParent(editor);
                controller.fromCharacter(character);
                controllers.add(controller);
                panes.add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected Void call() {
        findCharacters(targetString);
        System.out.println(characters.size());
        createListOfCharacters();
        return null;
    }

    public void findCharacters(String target) {
        if (target.startsWith("\"")) {
            loadByName(target);
        } else {
            loadByID(target);
        }
    }

    private void loadByID(String name) {
        System.out.println("Loading by ID");
        for (GameCharacter character : initialCharacters) {
            String expected = character.getIdentification();
            if (Strings.containsIgnoreCase(expected, name)) {
                characters.add(character);
            }
        }
    }

    private void loadByName(String name) {
        final String targetName = name.replace("\"", "");
        LoadedData data = editor.getParent().getSavedData().loadedData();
        DataPool<String> localisationPool = data.getLocalisationData();
        for (GameCharacter character : initialCharacters) {
            String expected = localisationPool.get(character.getName());
            if (Strings.containsIgnoreCase(expected, targetName)) {
                characters.add(character);
            }
        }
    }

    public List<CharacterItemController> getControllers() {
        return controllers;
    }

    public ObservableList<Pane> getPanes() {
        return panes;
    }

}

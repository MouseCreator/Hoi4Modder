package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.service.AbstractFactory;
import javafx.concurrent.Task;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

public class LoadingTask extends Task<Void> {

    private GameCharacterList characters;

    private CharacterListEditor editor;

    private final ListView<Pane> charactersListView;

    private final List<CharacterItemController> controllerList;

    private final String filename;

    public LoadingTask(CharacterListEditor editor, String filename, GameCharacterList characters,
                       ListView<Pane> charactersListView, List<CharacterItemController> controllers) {
        this.characters = characters;
        this.editor = editor;
        this.filename = filename;
        this.charactersListView = charactersListView;
        this.controllerList = controllers;
    }


    private void createListOfCharacters() {
        charactersListView.getItems().clear();
        for (GameCharacter character : characters) {
            try {
                editor.loadItem(character);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!charactersListView.getItems().isEmpty())
            charactersListView.scrollTo(0);
    }

    @Override
    protected Void call() throws Exception {
        this.characters = AbstractFactory.get().getCharacterList(filename);
        createListOfCharacters();
        return null;
    }
}

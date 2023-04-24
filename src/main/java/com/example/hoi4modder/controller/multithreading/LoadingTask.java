package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.service.AbstractFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

public class LoadingTask implements Runnable{

    private final GameCharacterList characters;

    private CharacterListEditor editor;

    private final ListView<Pane> charactersListView;

    private final List<CharacterItemController> controllerList;

    private String filename;

    public LoadingTask(CharacterListEditor editor, String filename, GameCharacterList characters,
                       ListView<Pane> charactersListView, List<CharacterItemController> controllers) {
        this.characters = characters;
        this.editor = editor;
        this.filename = filename;
        this.charactersListView = charactersListView;
        this.controllerList = controllers;
    }
    @Override
    public void run() {
        try {
            AbstractFactory.get().getCharacterList(filename);
            createListOfCharacters();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void createListOfCharacters() {
        charactersListView.getItems().clear();
        for (GameCharacter character : characters) {
            try {
                loadItem(character);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!charactersListView.getItems().isEmpty())
            charactersListView.scrollTo(0);
    }
    private void loadItem(GameCharacter character) throws IOException {
        FXMLLoader itemLoader = new FXMLLoader();
        itemLoader.setLocation(getClass().getResource("character-item.fxml"));
        Pane pane = itemLoader.load();
        charactersListView.getItems().add(pane);
        CharacterItemController controller = itemLoader.getController();
        controller.setParent(editor);
        controller.fromCharacter(character);
        controllerList.add(controller);
    }
}

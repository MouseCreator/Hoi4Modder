package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.service.AbstractFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoadingTask extends Task<Void> {

    private final GameCharacterList characters;
    private final String filename;
    private final CharacterListEditor editor;
    private final List<Pane> paneList = new ArrayList<>();
    private final List<CharacterItemController> controllerList = new ArrayList<>();

    public LoadingTask(CharacterListEditor editor, String filename, GameCharacterList characters) {
        this.characters = characters;
        this.filename = filename;
        this.editor = editor;
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
                controllerList.add(controller);
                paneList.add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected Void call() throws Exception {
        characters.clear();
        AbstractFactory.get().getCharacterList(characters, filename);
        createListOfCharacters();
        return null;
    }

    public List<CharacterItemController> getControllers() {
        return controllerList;
    }

    public ObservableList<Pane> getPanes() {
        return FXCollections.observableList(paneList);
    }
}

package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.service.AbstractFactory;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class LoadingTask extends Task<Void> {

    private final GameCharacterList characters;
    private final String filename;
    private final CharacterListEditor editor;

    private final List<Pane> paneList = new LinkedList<>();
    private final List<CharacterItemController> controllerList = new LinkedList<>();


    public LoadingTask(CharacterListEditor editor, String filename) {
        this.characters = GameCharacterList.getArrayList();
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
        AbstractFactory.get().getCharacterList(characters, filename);
        createListOfCharacters();
        return null;
    }

    public List<CharacterItemController> getControllers() {
        return controllerList;
    }

    public Pane[] getPanes() {
        Pane[] panes = new Pane[paneList.size()];
        return paneList.toArray(panes);
    }
}

package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
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
    public List<CharacterItemController> getControllers() {
        return controllerList;
    }

    public ObservableList<Pane> getPanes() {
        return paneList;
    }
}

package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.ItemContainer;
import com.example.hoi4modder.controller.character_extra.GameCharacterCreator;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * Task, that has to be executed on separate thread in item container (Character editor)
 */
public abstract class EditorListTask extends Task<Void> {
    protected GameCharacterList characters;
    protected ItemContainer<GameCharacter> editor;
    protected ObservableList<Pane> paneList = FXCollections.observableArrayList();
    protected List<CharacterItemController> controllerList = new ArrayList<>();

    /**
     *
     * @param container - items container to be changed
     * @param characters - list of characters
     */
    public EditorListTask(ItemContainer<GameCharacter> container, GameCharacterList characters) {
        this.editor = container;
        this.characters = characters;
    }

    /**
     * Creates observable list to be displayed
     */
    protected void createListOfCharacters() {
        GameCharacterCreator creator = new GameCharacterCreator(editor, paneList, controllerList);
        for (GameCharacter character : characters) {
            creator.addItem(character);
        }
    }

    /**
     *
     * @return controllers for generated list items
     */
    public List<CharacterItemController> getControllers() {
        return controllerList;
    }

    /**
     *
     * @return generated user interface list items
     */
    public ObservableList<Pane> getPanes() {
        return paneList;
    }
}

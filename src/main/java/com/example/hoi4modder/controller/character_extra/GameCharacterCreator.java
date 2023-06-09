package com.example.hoi4modder.controller.character_extra;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.ItemContainer;
import com.example.hoi4modder.game.GameCharacter;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

/**
 * Adds characters ot the list
 */
public class GameCharacterCreator implements ListCreator<GameCharacter> {
    private final ItemContainer<GameCharacter> parentController;
    private final ObservableList<Pane> panes;
    private final List<CharacterItemController> controllerList;

    /**
     * Constructor for game character creator
     * @param controller - list controller
     * @param panes - observable list, that is in the item container
     * @param controllerList - list of controllers of panes
     */
    public GameCharacterCreator(ItemContainer<GameCharacter> controller, ObservableList<Pane> panes,
                                List<CharacterItemController> controllerList) {
        this.parentController = controller;
        this.panes = panes;
        this.controllerList = controllerList;
    }

    /**
     * Adds item in the end of list
     * @param character - character to add
     */
    @Override
    public void addItem(GameCharacter character) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CharacterItemController.class.getResource("character-item.fxml"));
        try {
            Pane pane = loader.load();
            CharacterItemController controller = loader.getController();
            parentController.associateItem(controller);
            controller.fromModel(character);
            controllerList.add(controller);
            panes.add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds item at index
     * @param index - index to add
     * @param character - character to add
     */
    @Override
    public void addItemAt(int index, GameCharacter character) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CharacterItemController.class.getResource("character-item.fxml"));
        try {
            Pane pane = loader.load();
            CharacterItemController controller = loader.getController();
            parentController.associateItem(controller);
            controller.fromModel(character);
            controllerList.add(index, controller);
            panes.add(index, pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

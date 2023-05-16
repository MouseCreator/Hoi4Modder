package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.controller.ListItemController;
import com.example.hoi4modder.controller.character_extra.GameCharacterCreator;
import com.example.hoi4modder.controller.command.*;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Request handler for game character editor list
 */
public class CharacterEditorRequestHandler implements CommandRequestHandler<GameCharacter>{
    private final CharacterListEditor characterListEditor;
    /**
     *
     * @param editor - editor, that uses handler
     */
    public CharacterEditorRequestHandler(CharacterListEditor editor) {
        this.characterListEditor = editor;
    }

    /**
     *
     * @param elementId - element identification
     * @return true, if element with such identification exists
     */
    @Override
    public boolean handleContains(String elementId) {
        GameCharacterList gameCharacters = characterListEditor.getCharacters();
        for (GameCharacter gameCharacter : gameCharacters) {
            if (gameCharacter.getIdentification().equals(elementId)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param model - character to duplicate;
     * It is supposed that model is present in displayed list
     * It determines the index of element in model list and index of visual element
     */
    @Override
    public void handleDuplicate(GameCharacter model) {
        DuplicateCharacterCommand duplicateCharacterCommand = new DuplicateCharacterCommand(characterListEditor, model);
        characterListEditor.getHistory().add(duplicateCharacterCommand);
        duplicateCharacterCommand.execute();
    }

    /**
     * Duplicates character
     * @param model - character to duplicate
     * @return copy of character
     */
    public GameCharacter duplicateCommand(GameCharacter model) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        GameCharacter gameCharacter = characters.duplicate(findModelIndexOf(model));
        int visualIndex = findVisualIndexOf(model);
        if (visualIndex != -1) {
            GameCharacterCreator creator = new GameCharacterCreator(characterListEditor, listView.getItems(),
                    characterListEditor.getControllers());
            creator.addItemAt(visualIndex, gameCharacter);
        }
        return gameCharacter;
    }

    /**
     * Removes character from the list and adds it to command history
     * @param gameCharacter - character to remove
     */
    @Override
    public void handleRemove(GameCharacter gameCharacter) {
        DeleteCharacterCommand deleteCharacterCommand = new DeleteCharacterCommand(gameCharacter, characterListEditor);
        characterListEditor.getHistory().add(deleteCharacterCommand);
        deleteCharacterCommand.execute();
    }

    /**
     *
     * @param character - target character
     * @return index of element in model list
     */
    private int findModelIndexOf(GameCharacter character) {
        return characterListEditor.getCharacters().indexOf(character);
    }

    /**
     *
     * @param character - target element
     * @return index of element in the listview
     */
    private int findVisualIndexOf(GameCharacter character) {
        List<CharacterItemController> controllerList = characterListEditor.getControllers();
        for (int i = 0; i < controllerList.size(); i++) {
            ListItemController<GameCharacter> controller = controllerList.get(i);
            if (controller.toModel().equals(character)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes character from model list;
     * Removes controller and pane, if they are displayed.
     * @param gameCharacter - character to remove
     * @return visual index of the removed character or -1 if element is not displayed
     */
    public int removeCommand(GameCharacter gameCharacter) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        List<CharacterItemController> controllerList = characterListEditor.getControllers();
        characters.remove(gameCharacter);
        for (int i = 0; i < controllerList.size(); i++) {
            ListItemController<GameCharacter> controller = controllerList.get(i);
            if (controller.toModel().equals(gameCharacter)) {
                controllerList.remove(i);
                listView.getItems().remove(i);
                return i;
            }
        }
        return -1;
    }
    /**
     *
     * @param after - character, after which new character has to be added
     */
    @Override
    public void handleAdd(GameCharacter after) {
        AddAfterCommand addAfterCommand = new AddAfterCommand(characterListEditor, after);
        characterListEditor.getHistory().add(addAfterCommand);
        addAfterCommand.execute();
    }

    @Override
    public void handleConnect(TextField field, ControlConnectableCallable controlConnectableCallable) {
        CommandBinder.get().connectableCommand(characterListEditor.getHistory(), controlConnectableCallable,field);
    }

    @Override
    public int handleVisualIndex(GameCharacter model) {
        return findVisualIndexOf(model);
    }

    @Override
    public ControlConnectableCallable handleConnect(GameCharacter gameCharacter) {
        return new ControlConnectableCallable() {
            @Override
            public ControlConnectable call() {
                int index = handleVisualIndex(gameCharacter);
                return characterListEditor.getControllers().get(index);
            }
        };
    }

    /**
     * Adds new empty character after selected
     * @param after - support element, after which new item will be added
     * @return created item
     */
    @Override
    public GameCharacter addCommand(GameCharacter after) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        GameCharacter gameCharacter = GameCharacter.getSampleCharacter();
        characters.addAfter(gameCharacter, after);
        if (findVisualIndexOf(after) != -1) {
            GameCharacterCreator creator = new GameCharacterCreator(characterListEditor, listView.getItems(),
                    characterListEditor.getControllers());
            creator.addItemAt(findVisualIndexOf(after), gameCharacter);
        }
        return gameCharacter;
    }

    /**
     *
     * @return simple string representation of the duplicate command
     */
    @Override
    public String toString() {
        return "Duplicate command";
    }
}

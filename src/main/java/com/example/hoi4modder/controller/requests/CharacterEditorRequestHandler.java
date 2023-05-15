package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.controller.ListItemController;
import com.example.hoi4modder.controller.character_extra.GameCharacterCreator;
import com.example.hoi4modder.controller.command.DeleteCharacterCommand;
import com.example.hoi4modder.controller.command.DuplicateCharacterCommand;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Request handler for game character editor list
 */
public class CharacterEditorRequestHandler implements RequestHandler<GameCharacter>{
    private final CharacterListEditor characterListEditor;
    /**
     *
     * @param editor - editor, that uses handler
     */
    public CharacterEditorRequestHandler(CharacterListEditor editor) {
        this.characterListEditor = editor;
    }
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
    @Override
    public void handleRemove(GameCharacter gameCharacter) {
        DeleteCharacterCommand deleteCharacterCommand = new DeleteCharacterCommand(gameCharacter, characterListEditor);
        characterListEditor.getHistory().add(deleteCharacterCommand);
        deleteCharacterCommand.execute();
    }
    private int findModelIndexOf(GameCharacter character) {
        return characterListEditor.getCharacters().indexOf(character);
    }
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
    public int onRemoveCommand(GameCharacter gameCharacter) {
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
     * @param pane - GUI representation of the character
     */
    @Override
    public void handleAdd(GameCharacter after, Pane pane) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        GameCharacter gameCharacter = GameCharacter.getSampleCharacter();
        characters.addAfter(gameCharacter, after);
        GameCharacterCreator creator = new GameCharacterCreator(characterListEditor, listView.getItems(),
                characterListEditor.getControllers());
        creator.addItemAt(listView.getItems().indexOf(pane), gameCharacter);
    }

    @Override
    public String toString() {
        return "Duplicate command";
    }
}

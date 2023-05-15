package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.controller.ListItemController;
import com.example.hoi4modder.controller.character_extra.GameCharacterCreator;
import com.example.hoi4modder.controller.command.DeleteCharacterCommand;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.NoSuchElementException;

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
     * @param model - character to duplicate
     * @param pane - character GUI representation
     */
    @Override
    public void handleDuplicate(GameCharacter model, Pane pane) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        GameCharacter gameCharacter = characters.duplicate(characters.indexOf(model));
        GameCharacterCreator creator = new GameCharacterCreator(characterListEditor, listView.getItems(),
                characterListEditor.getControllers());
        creator.addItemAt(listView.getItems().indexOf(pane), gameCharacter);
    }
    @Override
    public void handleRemove(GameCharacter gameCharacter) {
        DeleteCharacterCommand deleteCharacterCommand = new DeleteCharacterCommand(gameCharacter, characterListEditor);
        characterListEditor.getHistory().add(deleteCharacterCommand);
        deleteCharacterCommand.execute();
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
        throw new NoSuchElementException("Cannot find Game Character to remove");
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

}

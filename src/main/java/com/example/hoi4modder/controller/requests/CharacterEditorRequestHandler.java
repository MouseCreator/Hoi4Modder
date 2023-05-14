package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.controller.ListItemController;
import com.example.hoi4modder.controller.character_extra.GameCharacterCreator;
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

    /**
     *
     * @param controller - controller for GUI representation of character
     * @param pane - character GUI representation
     */
    @Override
    public void handleRemove(ListItemController<GameCharacter> controller, Pane pane) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        List<CharacterItemController> controllerList = characterListEditor.getControllers();
        characters.remove(controller.toModel());
        controllerList.remove((CharacterItemController) controller);
        listView.getItems().remove(pane);
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

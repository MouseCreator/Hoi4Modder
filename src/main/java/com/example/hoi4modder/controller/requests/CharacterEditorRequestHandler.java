package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
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
     * @param request - receive the request to be executed
     */
    @Override
    public void onRequest(Request<GameCharacter> request){
        request.handleWith(this);
    }

    /**
     *
     * @param editor - editor, that uses handler
     */
    public CharacterEditorRequestHandler(CharacterListEditor editor) {
        this.characterListEditor = editor;
    }
    @Override
    public void handle(ItemPresentRequest<GameCharacter> request) {
        String id = request.getId();
        GameCharacterList gameCharacters = characterListEditor.getCharacters();
        for (GameCharacter gameCharacter : gameCharacters) {
            if (gameCharacter.getIdentification().equals(id)) {
                request.setResult(true);
                return;
            }
        }
        request.setResult(false);
    }

    /**
     *
     * @param request - duplicates game character and puts it above origin
     */
    @Override
    public void handle(DuplicateRequest<GameCharacter> request) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        GameCharacter gameCharacter = characters.duplicate(characters.indexOf(request.model()));
        GameCharacterCreator creator = new GameCharacterCreator(characterListEditor, listView.getItems(),
                characterListEditor.getControllers());
        creator.addItemAt(listView.getItems().indexOf(request.pane()), gameCharacter);
    }

    /**
     *
     * @param request - removes selected item
     */
    @Override
    public void handle(RemoveRequest<GameCharacter> request) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        List<CharacterItemController> controllerList = characterListEditor.getControllers();
        characters.remove(request.controller().toModel());
        controllerList.remove((CharacterItemController) request.controller());
        listView.getItems().remove(request.pane());
    }

    /**
     *
     * @param request - adds item above current, making it easy to access
     */
    @Override
    public void handle(AddRequest<GameCharacter> request) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        GameCharacter gameCharacter = GameCharacter.getSampleCharacter();
        characters.addAfter(gameCharacter, request.after());
        GameCharacterCreator creator = new GameCharacterCreator(characterListEditor, listView.getItems(),
                characterListEditor.getControllers());
        creator.addItemAt(listView.getItems().indexOf(request.pane()), gameCharacter);
    }

}

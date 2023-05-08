package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.controller.character_extra.GameCharacterCreator;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.List;

public class CharacterEditorRequestHandler implements RequestHandler<GameCharacter>{
    private final CharacterListEditor characterListEditor;

    @Override
    public void onRequest(Request<GameCharacter> request){
        request.handleWith(this);
    }

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

    @Override
    public void handle(DuplicateRequest<GameCharacter> request) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        GameCharacter gameCharacter = characters.duplicate(characters.indexOf(request.model()));
        GameCharacterCreator creator = new GameCharacterCreator(characterListEditor, listView.getItems(),
                characterListEditor.getControllers());
        creator.addItemAt(listView.getItems().indexOf(request.pane()), gameCharacter);
    }
    @Override
    public void handle(RemoveRequest<GameCharacter> request) {
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        List<CharacterItemController> controllerList = characterListEditor.getControllers();
        characters.remove(request.controller().toModel());
        listView.getItems().remove(request.pane());
        controllerList.remove((CharacterItemController) request.controller());
    }

}

package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.controller.character_extra.GameCharacterCreator;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class CharacterEditorRequestHandler implements RequestHandler{
    private final CharacterListEditor characterListEditor;

    public CharacterEditorRequestHandler(CharacterListEditor editor) {
        this.characterListEditor = editor;
    }
    @Override
    public void handle(ItemPresentRequest request) {
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
    public void handle(DuplicateRequest request) {
        Pane target = request.pane();
        GameCharacterList characters = characterListEditor.getCharacters();
        ListView<Pane> listView = characterListEditor.getItems();
        int indexToDuplicate = listView.getItems().indexOf(target);
        GameCharacter gameCharacter = characters.duplicate(indexToDuplicate);
        GameCharacterCreator creator = new GameCharacterCreator(characterListEditor, listView.getItems(),
                characterListEditor.getControllers());
        creator.addItemAt(indexToDuplicate, gameCharacter);
    }

    @Override
    public void onRequest(Request request){
        request.handleWith(this);
    }

}

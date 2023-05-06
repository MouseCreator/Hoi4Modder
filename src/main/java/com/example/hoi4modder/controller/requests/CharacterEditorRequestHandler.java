package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;

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
    public void onRequest(Request request){
        request.handleWith(this);
    }

}

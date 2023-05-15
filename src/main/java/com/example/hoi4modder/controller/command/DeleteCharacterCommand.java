package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;

public class DeleteCharacterCommand implements Command{

    public DeleteCharacterCommand(GameCharacter gameCharacter, CharacterListEditor editor) {
        this.gameCharacter = gameCharacter;
        this.editor = editor;
    }
    private final GameCharacter gameCharacter;
    private final CharacterListEditor editor;

    private int index;
    private int visualIndex;
    @Override
    public void execute() {
        index = editor.getCharacters().indexOf(gameCharacter);
        visualIndex = editor.getHandler().onRemoveCommand(gameCharacter);
    }

    @Override
    public void undo() {
        editor.addCharacterAt(index, visualIndex, gameCharacter);
    }

    @Override
    public String toString() {
        return "Delete command";
    }
}

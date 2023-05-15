package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;
public class DuplicateCharacterCommand implements Command{

    public DuplicateCharacterCommand(CharacterListEditor editor, GameCharacter model) {
        this.editor = editor;
        this.character = model;
        this.originID = character.getIdentification();
    }
    private GameCharacter character;

    private final String originID;
    private final CharacterListEditor editor;
    @Override
    public void execute() {
        this.character = editor.getHandler().duplicateCommand(character);
    }

    @Override
    public void undo() {
        editor.getHandler().removeCommand(character);
    }

    @Override
    public String toString() {
        return "Duplicate command: duplicated " + originID;
    }
}

package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;

public class AddAfterCommand implements Command{

    public AddAfterCommand(CharacterListEditor editor, GameCharacter after) {
        this.addedCharacter = after;
        this.editor = editor;
    }

    private final CharacterListEditor editor;
    private GameCharacter addedCharacter;
    @Override
    public void execute() {
        addedCharacter = editor.getHandler().addCommand(addedCharacter);
    }

    @Override
    public void undo() {
        editor.getHandler().removeCommand(addedCharacter);
    }
}

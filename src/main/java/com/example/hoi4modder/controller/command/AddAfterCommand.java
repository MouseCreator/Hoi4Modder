package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;

public class AddAfterCommand implements Command{

    public AddAfterCommand(CharacterListEditor editor, GameCharacter after) {
        this.after = after;
        this.editor = editor;
    }

    private final CharacterListEditor editor;
    private GameCharacter addedCharacter;

    private final GameCharacter after;
    @Override
    public void execute() {
        addedCharacter = editor.getHandler().addCommand(after);
    }

    @Override
    public void undo() {
        editor.getHandler().removeCommand(addedCharacter);
    }
}

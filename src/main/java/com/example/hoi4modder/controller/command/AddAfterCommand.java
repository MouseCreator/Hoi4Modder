package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;

public class AddAfterCommand implements Command{
    /**
     *
     * @param editor - editor, containing list of characters
     * @param after - character, after which the new character has to be added
     */
    public AddAfterCommand(CharacterListEditor editor, GameCharacter after) {
        this.after = after;
        this.editor = editor;
    }

    private final CharacterListEditor editor;
    private GameCharacter addedCharacter;

    private final GameCharacter after;

    /**
     * Adds new character
     */
    @Override
    public void execute() {
        addedCharacter = editor.getHandler().addCommand(after);
    }

    /**
     * Removes added character
     */
    @Override
    public void undo() {
        if (addedCharacter == null) {
            throw new IllegalStateException("Trying to remove character that has not benn added yet.");
        }
        editor.getHandler().removeCommand(addedCharacter);
    }
}

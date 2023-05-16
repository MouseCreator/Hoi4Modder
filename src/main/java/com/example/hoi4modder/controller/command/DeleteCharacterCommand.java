package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;

/**
 * Command to delete character
 */
public class DeleteCharacterCommand implements Command{
    /**
     * Delete character command constructor
     * @param gameCharacter - character to remove
     * @param editor - editor, containing list of all characters
     */
    public DeleteCharacterCommand(GameCharacter gameCharacter, CharacterListEditor editor) {
        this.gameCharacter = gameCharacter;
        this.editor = editor;
    }
    private final GameCharacter gameCharacter;
    private final CharacterListEditor editor;

    private int index;
    private int visualIndex;

    /**
     * Removes character from the list
     */
    @Override
    public void execute() {
        index = editor.getCharacters().indexOf(gameCharacter);
        visualIndex = editor.getHandler().removeCommand(gameCharacter);
    }

    /**
     * Restores the removed character
     */
    @Override
    public void undo() {
        editor.addCharacterAt(index, Math.max(visualIndex, 0), gameCharacter);
    }

    /**
     *
     * @return Simple string representation of the command
     */
    @Override
    public String toString() {
        return "Delete command";
    }
}

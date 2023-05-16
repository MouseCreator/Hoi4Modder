package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;

/**
 * Creates copy of game character
 */
public class DuplicateCharacterCommand implements Command{
    /**
     *
     * @param editor - editor, containing all characters
     * @param model - model (character) to duplicate
     */
    public DuplicateCharacterCommand(CharacterListEditor editor, GameCharacter model) {
        this.editor = editor;
        this.character = model;
        this.originID = character.getIdentification();
    }
    private GameCharacter character;
    private final String originID;
    private final CharacterListEditor editor;

    /**
     * Duplicates character
     */
    @Override
    public void execute() {
        this.character = editor.getHandler().duplicateCommand(character);
    }

    /**
     * Removes duplicate of the character
     */
    @Override
    public void undo() {
        editor.getHandler().removeCommand(character);
    }

    /**
     *
     * @return String representation of duplicate command, containing ID of the character to duplicate
     */
    @Override
    public String toString() {
        return "Duplicate command: duplicated " + originID;
    }
}

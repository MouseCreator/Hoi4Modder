package com.example.hoi4modder.controller.command;


import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;

public class CreateCharacterCommand implements Command{
    private final CharacterListEditor editor;
    private GameCharacter addedItem;
    public CreateCharacterCommand(CharacterListEditor editor) {
        this.editor = editor;
        int lastIndex = editor.getControllers().size()-1;
        addedItem = editor.getCharacters().get(lastIndex);
    }

    /**
     * Adds new empty character in the end of the list
     */
    @Override
    public void execute() {
        editor.pushCharacter();
        int lastIndex = editor.getControllers().size()-1;
        this.addedItem = editor.getCharacters().get(lastIndex);
    }

    /**
     * Removes character that was added
     */
    @Override
    public void undo() {
        editor.getHandler().removeCommand(addedItem);
    }

    @Override
    public String toString() {
        return "Create character command";
    }
}

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
    @Override
    public void execute() {
        editor.pushCharacter();
        int lastIndex = editor.getControllers().size()-1;
        this.addedItem = editor.getCharacters().get(lastIndex);
    }

    @Override
    public void undo() {
        editor.getHandler().handleRemove(addedItem);
    }

    @Override
    public String toString() {
        return "Create character command";
    }
}

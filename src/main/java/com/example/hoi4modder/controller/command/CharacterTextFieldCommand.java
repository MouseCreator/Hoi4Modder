package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterListEditor;

public class CharacterTextFieldCommand extends CharacterItemCommand{

    private final String oldValue;
    private final String newValue;
    private final int control;
    public CharacterTextFieldCommand(CharacterListEditor editor, int visualIndex, int control, String oldValue, String newValue) {
        super(editor, visualIndex);
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.control = control;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}

package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;

public abstract class CharacterItemCommand implements Command{
    private final int visualIndex;
    protected final CharacterListEditor editor;
    public CharacterItemCommand(CharacterListEditor editor, int visualIndex) {
        this.editor =  editor;
        this.visualIndex = visualIndex;
    }
    protected CharacterItemController getItemController() {
        return editor.getControllers().get(visualIndex);
    }
}

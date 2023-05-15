package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.CharacterItemController;
import com.example.hoi4modder.controller.CharacterListEditor;
import javafx.scene.layout.Pane;

public class CreateCharacterCommand implements Command{

    private final CharacterListEditor editor;
    private Pane pane;
    private CharacterItemController itemController;
    public CreateCharacterCommand(CharacterListEditor editor) {
        this.editor = editor;
        int lastIndex = editor.getControllers().size()-1;
        this.pane = editor.getItems().getItems().get(lastIndex);
        this.itemController = editor.getControllers().get(lastIndex);
    }
    @Override
    public void execute() {
        editor.pushCharacter();
        int lastIndex = editor.getControllers().size()-1;
        this.pane = editor.getItems().getItems().get(lastIndex);
        this.itemController = editor.getControllers().get(lastIndex);
    }

    @Override
    public void undo() {
        editor.getHandler().handleRemove(itemController, pane);
    }

    @Override
    public String toString() {
        return "Create character command";
    }
}

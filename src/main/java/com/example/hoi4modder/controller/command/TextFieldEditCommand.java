package com.example.hoi4modder.controller.command;

import javafx.scene.control.TextField;
public class TextFieldEditCommand implements Command {
    public TextFieldEditCommand(TextField textField, String oldText, String newText) {
        this.trackedTextField = textField;
        this.oldText = oldText;
        this.newText = newText;
    }
    private final TextField trackedTextField;
    private final String oldText;
    private final String newText;
    @Override
    public void execute() {
        trackedTextField.setText(newText);
    }

    @Override
    public void undo() {
        trackedTextField.setText(oldText);
    }
}

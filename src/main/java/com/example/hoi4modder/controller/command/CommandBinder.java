package com.example.hoi4modder.controller.command;

import javafx.scene.control.TextField;

/**
 * Singleton class, used to connect changeable objects with history
 */
public class CommandBinder {
    private static CommandBinder commandBinder;
    private CommandBinder() {}
    public static CommandBinder get() {
        if (commandBinder == null) {
            commandBinder = new CommandBinder();
        }
        return commandBinder;
    }
    public void bindTextField(History history, TextField textField) {
        BindMemory<String> binding = new BindMemory<>();
        textField.focusedProperty().addListener((observable, oldValue, isNowSelected) -> {
            if (isNowSelected) {
                binding.setLastString(textField.getText());
            } else {
                String newText = textField.getText();
                if (binding.getLastString().equals(newText))
                    return;
                history.add(new TextFieldEditCommand(textField, binding.getLastString(), newText));
            }
        });
    }
}

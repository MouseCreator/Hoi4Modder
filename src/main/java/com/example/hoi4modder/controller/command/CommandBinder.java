package com.example.hoi4modder.controller.command;

import javafx.scene.control.ComboBox;
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
                binding.setMemory(textField.getText());
            } else {
                String newText = textField.getText();
                if (binding.getMemory().equals(newText))
                    return;
                history.add(new TextFieldEditCommand(textField, binding.getMemory(), newText));
            }
        });
    }

    public void bindComboBox(History history, ComboBox<String> comboBox) {
        BindMemory<Integer> binding = new BindMemory<>();
        comboBox.focusedProperty().addListener((observable, oldValue, isNowSelected) -> {
            if (isNowSelected) {
                binding.setMemory(comboBox.getSelectionModel().getSelectedIndex());
            } else {
                Integer newSelection = comboBox.getSelectionModel().getSelectedIndex();
                if (binding.getMemory().equals(newSelection))
                    return;
                history.add(new ComboboxEditCommand(comboBox, binding.getMemory(), newSelection));
            }
        });
    }
}

package com.example.hoi4modder.controller.command;

import com.example.hoi4modder.controller.command.history.History;
import com.example.hoi4modder.controller.command.roles.UndoRedoManager;
import javafx.scene.control.CheckBox;
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

    /**
     * Connects history and text field
     * @param history - history of commands
     * @param textField - text field to keep track of
     */
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

    public void connectableCommand(History history, ControlConnectableCallable controlConnectableCallable,
                                   TextField textField, UndoRedoManager undoRedoManager) {
        BindMemory<String> binding = new BindMemory<>();
        textField.focusedProperty().addListener((observable, oldValue, isNowSelected) -> {
            if (!undoRedoManager.isEnabled(textField))
                return;
            if (isNowSelected) {
                binding.setMemory(textField.getText());
            } else {
                String newText = textField.getText();
                if (binding.getMemory().equals(newText))
                    return;
                int index = controlConnectableCallable.call().getConnector().getIndexOf(textField);
                history.add(new CharacterTextFieldCommand(controlConnectableCallable, index ,binding.getMemory(), newText));
            }
        });
    }

    public void connectableCommand(History history, ControlConnectableCallable controlConnectableCallable,
                                   CheckBox checkBox, UndoRedoManager undoRedoManager) {
        checkBox.selectedProperty().addListener((observable, oldValue, isNowSelected) -> {
            if (!undoRedoManager.isEnabled(checkBox))
                return;
            if (oldValue != isNowSelected) {
                if (history.isRedo() || history.isUndo()) {
                    return;
                }
                int index = controlConnectableCallable.call().getConnector().getIndexOf(checkBox);
                history.add(new CharacterCheckBoxCommand(controlConnectableCallable, index, oldValue, isNowSelected));
            }
        });
    }
    public void connectableCommand(History history, ControlConnectableCallable controlConnectableCallable,
                                   ComboBox<String> comboBox, UndoRedoManager undoRedoManager) {
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!undoRedoManager.isEnabled(comboBox))
                return;
            if (newValue.equals(oldValue))
                return;
            if (history.isRedo() || history.isUndo()) {
                return;
            }
            int index = controlConnectableCallable.call().getConnector().getIndexOf(comboBox);

            history.add(new CharacterComboBoxCommand(controlConnectableCallable, index ,oldValue, newValue));
        });
    }
}

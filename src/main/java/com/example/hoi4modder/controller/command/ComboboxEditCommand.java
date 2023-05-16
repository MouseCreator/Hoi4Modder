package com.example.hoi4modder.controller.command;

import javafx.scene.control.ComboBox;

/**
 * Command to change value in the combobox
 */
public class ComboboxEditCommand implements Command {
    private final ComboBox<String> comboBox;
    private final Integer oldSelected;
    private final Integer newSelected;
    public ComboboxEditCommand(ComboBox<String> comboBox, Integer memory, Integer newSelection) {
        this.comboBox = comboBox;
        this.oldSelected = memory;
        this.newSelected = newSelection;
    }

    /**
     * Changes to new value
     */
    @Override
    public void execute() {
        comboBox.getSelectionModel().select(newSelected);
    }

    /**
     * Changes to old value
     */
    @Override
    public void undo() {
        comboBox.getSelectionModel().select(oldSelected);
    }
}

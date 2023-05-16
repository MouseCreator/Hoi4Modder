package com.example.hoi4modder.controller.command;

import javafx.scene.control.ComboBox;

public class CharacterComboBoxCommand extends CharacterItemCommand {

    private final int index;
    private final String oldValue;
    private final String newValue;
    public CharacterComboBoxCommand(ControlConnectableCallable controlConnectableCallable, int index, String oldValue, String newValue) {
        super(controlConnectableCallable);
        this.oldValue = oldValue;
        this.index = index;
        this.newValue = newValue;
    }

    @Override
    public void execute() {
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>) controlConnectableCallable.call().getConnector().getFieldByIndex(index);
        comboBox.getSelectionModel().select(newValue);
    }

    @Override
    public void undo() {
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>) controlConnectableCallable.call().getConnector().getFieldByIndex(index);
        comboBox.getSelectionModel().select(oldValue);
    }
}

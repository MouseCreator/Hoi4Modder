package com.example.hoi4modder.controller.command;

import javafx.scene.control.CheckBox;

public class CharacterCheckBoxCommand extends CharacterItemCommand {

    private final int index;
    private final boolean oldValue;
    private final boolean newValue;
    public CharacterCheckBoxCommand(ControlConnectableCallable controlConnectableCallable, int index,
                                    boolean old, boolean newValue) {
        super(controlConnectableCallable);
        this.oldValue = old;
        this.index = index;
        this.newValue = newValue;
    }

    @Override
    public void execute() {
        CheckBox checkBox = (CheckBox) controlConnectableCallable.call().getConnector().getFieldByIndex(index);
        checkBox.setSelected(newValue);
    }

    @Override
    public void undo() {
        CheckBox checkBox = (CheckBox) controlConnectableCallable.call().getConnector().getFieldByIndex(index);
        checkBox.setSelected(oldValue);
    }
}

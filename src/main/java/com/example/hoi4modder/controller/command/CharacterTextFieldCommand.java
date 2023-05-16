package com.example.hoi4modder.controller.command;

import javafx.scene.control.TextField;


public class CharacterTextFieldCommand extends CharacterItemCommand{

    private final String oldValue;
    private final String newValue;
    private final int textFieldId;
    public CharacterTextFieldCommand(ControlCallable controlConnectableCallable, int control,
                                     String oldValue, String newValue) {
        super(controlConnectableCallable);
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.textFieldId = control;
    }

    @Override
    public void execute() {
        ControlConnectable connectable = getItemController();
        TextField field = (TextField) connectable.callSelf().call();
        field.setText(newValue);
    }

    @Override
    public void undo() {
        ControlConnectable connectable = getItemController();
        TextField field = (TextField) connectable.getConnector().getFieldByIndex(textFieldId);
        field.setText(oldValue);
    }

    @Override
    public String toString() {
        return "Character Field command: " + oldValue + "-->" + newValue;
    }
}

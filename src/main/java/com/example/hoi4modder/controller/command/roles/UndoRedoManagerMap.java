package com.example.hoi4modder.controller.command.roles;


import java.util.Map;

public class UndoRedoManagerMap implements UndoRedoManager {
    private Map<Object, ControlStatus> controls;

    public boolean isEnabled(Object object) {
        return controls.get(object).isListenerEnabled();
    }

    public void setEnabled(Object object, boolean value) {
        controls.get(object).setListenerEnabled(value);
    }
    public void addObjectStatus(Object object) {
        controls.put(object, new ControlStatus());
    }
    public void addObjectStatus(Object object, ControlStatus status) {
        controls.put(object, status);
    }

}

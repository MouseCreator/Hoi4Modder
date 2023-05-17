package com.example.hoi4modder.controller.command.roles;


import java.util.Map;

public class UndoRedoManagerMap implements UndoRedoManager {
    private final Map<Object, ControlStatus> controls;

    public UndoRedoManagerMap(Map<Object, ControlStatus> map) {
        this.controls = map;
    }


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

    @Override
    public void enableAll() {
        setAll(true);
    }

    @Override
    public void disableAll() {
        setAll(false);
    }

    private void setAll(boolean value) {
        for (ControlStatus status : controls.values())
            status.setListenerEnabled(value);
    }

}

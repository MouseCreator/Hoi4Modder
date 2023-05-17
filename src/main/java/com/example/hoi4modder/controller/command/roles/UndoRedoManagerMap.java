package com.example.hoi4modder.controller.command.roles;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class UndoRedoManagerMap implements UndoRedoManager {
    private Map<Object, ControlStatus> controls;

    private final Stack<Map<Object, ControlStatus>> stateMap;

    public UndoRedoManagerMap(Map<Object, ControlStatus> map) {
        this.controls = map;
        stateMap = new Stack<>();
    }


    public boolean isEnabled(Object object) {
        return controls.get(object).isListenerEnabled();
    }

    public void setEnabled(Object object, boolean value) {
        controls.get(object).setListenerEnabled(value);
    }
    public void addObjectStatus(Object object) {
        try {
            controls.put(object, new ControlStatus());
        } catch (Exception e ) {
            e.printStackTrace();
        }
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

    @Override
    public void saveState() {
        stateMap.push(new HashMap<>(controls));
    }

    @Override
    public void lastState() {
        this.controls = stateMap.pop();
    }

    private void setAll(boolean value) {
        for (ControlStatus status : controls.values())
            status.setListenerEnabled(value);
    }

}

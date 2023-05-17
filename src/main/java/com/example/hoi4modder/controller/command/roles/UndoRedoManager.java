package com.example.hoi4modder.controller.command.roles;

public interface UndoRedoManager {

    boolean isEnabled(Object object);

    void setEnabled(Object object, boolean value);
    void addObjectStatus(Object object);
    void addObjectStatus(Object object, ControlStatus status);
}

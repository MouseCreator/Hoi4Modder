package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.command.ControlConnectable;
import com.example.hoi4modder.controller.command.ControlConnectableCallable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Receives request and responds, using controller methods
 * @param <T> - generic value, inherited from controller
 */
public interface RequestHandler<T> {
    /**
     * Checks if item is present
     */
    boolean handleContains(String elementId);

    /**
     * Creates a duplicate of some element
     */
    void handleDuplicate(T model);
    /**
     * Removes element from the list
     */
    void handleRemove(T model);

    /**
     * Adds element to the list
     */
    void handleAdd(T after);

    int handleVisualIndex(T model);

    ControlConnectableCallable handleConnect(T gameCharacter);
    void handleConnect(TextField field, ControlConnectableCallable controlConnectableCallable);
    void handleConnect(CheckBox checkBox, ControlConnectableCallable controlConnectableCallable);
    void handleConnect(ComboBox<String> checkBox, ControlConnectableCallable controlConnectableCallable);
    void handleInitialization(ControlConnectable controller);
}

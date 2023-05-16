package com.example.hoi4modder.controller.requests;

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

    void handleConnect(TextField field, T model);
}

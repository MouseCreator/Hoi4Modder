package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.ListItemController;
import javafx.scene.layout.Pane;

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
    void handleDuplicate(T model, Pane pane);
    /**
     * Removes element from the list
     */
    void handleRemove(ListItemController<T> controller, Pane pane);

    /**
     * Adds element to the list
     */
    void handleAdd(T after, Pane pane);
}

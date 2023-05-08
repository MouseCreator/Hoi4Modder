package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.ListItemController;
import javafx.scene.layout.Pane;

/**
 * Requests to remove element from the list
 * @param controller - controller of the item
 * @param pane - element of the interface, that user is interested in
 * @param <T> - generic, common for item and controller
 */
public record RemoveRequest<T>(ListItemController<T> controller, Pane pane) implements Request<T> {

    @Override
    public void handleWith(RequestHandler<T> handler) {
        handler.handle(this);
    }
}

package com.example.hoi4modder.controller.requests;

import javafx.scene.layout.Pane;

/**
 * Requests handler to add item to list in specific position
 * @param after - position mark
 * @param pane - model ui representation
 * @param <T> - generic type for controller and item
 */
public record AddRequest<T>(T after, Pane pane) implements Request<T> {

    @Override
    public void handleWith(RequestHandler<T> handler) {
        handler.handle(this);
    }
}

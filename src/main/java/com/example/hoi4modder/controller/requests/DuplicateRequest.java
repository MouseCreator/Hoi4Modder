package com.example.hoi4modder.controller.requests;

import javafx.scene.layout.Pane;

/**
 * Requests handler to duplicate item
 * @param model - item to duplicate
 * @param pane - model representation
 * @param <T> - generic type for controller and item
 */
public record DuplicateRequest<T>(T model, Pane pane) implements Request<T> {

    @Override
    public void handleWith(RequestHandler<T> handler) {
        handler.handle(this);
    }
}

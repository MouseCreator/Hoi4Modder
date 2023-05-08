package com.example.hoi4modder.controller.requests;

import javafx.scene.layout.Pane;

public record DuplicateRequest<T>(T model, Pane pane) implements Request<T> {

    @Override
    public void handleWith(RequestHandler<T> handler) {
        handler.handle(this);
    }
}

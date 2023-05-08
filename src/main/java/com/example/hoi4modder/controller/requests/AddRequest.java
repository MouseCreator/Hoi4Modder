package com.example.hoi4modder.controller.requests;

import javafx.scene.layout.Pane;

public record AddRequest<T>(T after, Pane pane) implements Request<T> {

    @Override
    public void handleWith(RequestHandler<T> handler) {
        handler.handle(this);
    }
}

package com.example.hoi4modder.controller.requests;

import javafx.scene.layout.Pane;

public record DuplicateRequest(Pane pane) implements Request {

    @Override
    public void handleWith(RequestHandler handler) {
        handler.handle(this);
    }
}

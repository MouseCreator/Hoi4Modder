package com.example.hoi4modder.controller.requests;

import com.example.hoi4modder.controller.ListItemController;
import javafx.scene.layout.Pane;

public record RemoveRequest<T>(ListItemController<T> controller, Pane pane) implements Request<T> {

    @Override
    public void handleWith(RequestHandler<T> handler) {
        handler.handle(this);
    }
}

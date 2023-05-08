package com.example.hoi4modder.controller.requests;

public interface Request<T> {
    void handleWith(RequestHandler<T> handler);
}

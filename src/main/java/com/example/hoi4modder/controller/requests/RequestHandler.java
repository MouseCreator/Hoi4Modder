package com.example.hoi4modder.controller.requests;

public interface RequestHandler {
    void handle(ItemPresentRequest request);
    void onRequest(Request request);
}

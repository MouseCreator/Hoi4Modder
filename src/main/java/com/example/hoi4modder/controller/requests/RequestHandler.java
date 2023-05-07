package com.example.hoi4modder.controller.requests;

public interface RequestHandler {
    void handle(ItemPresentRequest request);
    void handle(DuplicateRequest request);
    void onRequest(Request request);
}

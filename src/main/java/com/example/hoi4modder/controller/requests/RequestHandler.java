package com.example.hoi4modder.controller.requests;

public interface RequestHandler<T> {
    void handle(ItemPresentRequest<T> request);
    void handle(DuplicateRequest<T> request);
    void onRequest(Request<T> request);

    void handle(RemoveRequest<T> removeRequest);

    void handle(AddRequest<T> tAddRequest);
}

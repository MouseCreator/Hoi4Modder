package com.example.hoi4modder.controller.requests;

public class ItemPresentRequest<T> implements Request<T>{
    private final String id;

    public boolean result = false;
    public ItemPresentRequest(String itemId) {
        this.id = itemId;
    }
    public String getId() {
        return id;
    }
    @Override
    public void handleWith(RequestHandler<T> handler) {
        handler.handle(this);
    }

    public void setResult(boolean b) {
        this.result = b;
    }
    public boolean getIsPresent() {
        return result;
    }
}

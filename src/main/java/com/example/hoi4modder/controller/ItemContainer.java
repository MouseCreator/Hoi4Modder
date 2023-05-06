package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.requests.Request;

public interface ItemContainer<T> {
    void associateItem(ListItemController<T> item);

    void handle(Request request);
}

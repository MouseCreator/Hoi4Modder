package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.requests.Request;
import com.example.hoi4modder.game.common.Country;

public interface ItemContainer<T> {
    void associateItem(ListItemController<T> item);

    void handle(Request request);

    MainController getMainController();

    Country getCountry();
}

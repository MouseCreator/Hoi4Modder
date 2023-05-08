package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.requests.Request;
import com.example.hoi4modder.game.common.Country;

/**
 * Controller for item of the list
 * @param <T> - generic type for model, contained in the list
 */
public interface ItemContainer<T> {
    /**
     *
     * @param item - controller to be associated, which means it will have current list controller as parent
     */
    void associateItem(ListItemController<T> item);

    /**
     * Receive and handle request
     * @param request - action to be executed on list
     */
    void handle(Request<T> request);

    /**
     *
     * @return main controller
     */
    MainController getMainController();

    /**
     *
     * @return current edited country
     */
    Country getCountry();
}

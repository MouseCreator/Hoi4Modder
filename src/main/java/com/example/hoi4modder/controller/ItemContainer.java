package com.example.hoi4modder.controller;

import com.example.hoi4modder.controller.command.history.History;
import com.example.hoi4modder.controller.requests.RequestHandler;
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
     *
     * @return main controller
     */
    MainController getMainController();

    /**
     *
     * @return current edited country
     */
    Country getCountry();

    /**
     *
     * @return request handler that items can use
     */
    RequestHandler<T> getHandler();

    /**
     *
     * @return request handler that items can use
     */
    History getHistory();
}

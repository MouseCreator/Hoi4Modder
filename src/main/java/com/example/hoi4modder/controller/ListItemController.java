package com.example.hoi4modder.controller;

/**
 * Controller for item in the list of models
 * @param <T> - model generic parameter
 */
public interface ListItemController<T> {
    /**
     * Creates item from model
     * @param model - model to create item from
     */
    void fromModel(T model);

    /**
     * Generates model from gui item
     * @return generated model
     */
    T toModel();

    /**
     * Connects item with list to send requests
     * @param editor - parent list
     */
    void setParent(ItemContainer<T> editor);
}

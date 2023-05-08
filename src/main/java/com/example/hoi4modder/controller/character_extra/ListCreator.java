package com.example.hoi4modder.controller.character_extra;

/**
 * Interface for creator of items in the list
 * @param <T> - generic type of the item container, specific list creator serves for
 */
public interface ListCreator<T> {
    /**
     * Adds item in the end of the list
     * @param item - item to add
     */
    void addItem(T item);

    /**
     * Adds item at certain position
     * @param index - index to add item
     * @param item - item to add
     */
    void addItemAt(int index, T item);
}

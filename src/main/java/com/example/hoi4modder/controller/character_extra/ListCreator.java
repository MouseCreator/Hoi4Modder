package com.example.hoi4modder.controller.character_extra;

public interface ListCreator<T> {
    void addItem(T item);

    void addItemAt(int index, T item);
}

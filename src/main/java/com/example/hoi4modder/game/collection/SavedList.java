package com.example.hoi4modder.game.collection;

import java.util.List;

public class SavedList<T> extends SavedCollection<T> {
    private List<T> list;
    public void add(T value) {
        list.add(value);
    }

    public boolean contains(T value) {
        return list.contains(value);
    }

    public boolean remove(T value) {
        return list.remove(value);
    }
}

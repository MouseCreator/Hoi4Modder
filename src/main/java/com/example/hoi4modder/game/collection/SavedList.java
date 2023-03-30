package com.example.hoi4modder.game.collection;

import java.util.List;

public class SavedList<T> extends SavedCollection<T> {
    private final List<T> list;

    public SavedList(List<T> v) {
        this.list = v;
    }

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

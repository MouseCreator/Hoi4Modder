package com.example.hoi4modder.game.collection;

import com.example.hoi4modder.game.FieldValueMap;


public class SavedMap<T> extends SavedCollection<T>{
    private final FieldValueMap<T> map;

    public SavedMap(FieldValueMap<T> map) {
        this.map = map;
    }
    public void put(String field, T value) {
        map.put(field, value);
    }
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }
    public boolean containsValue(T value) {
        return map.containsValue(value);
    }
}

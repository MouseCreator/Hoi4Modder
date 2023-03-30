package com.example.hoi4modder.game.collection;

import com.example.hoi4modder.game.FieldValueMap;

import java.util.Map;

public class SavedMap<T> extends SavedCollection<T>{
    private FieldValueMap<T> map;
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

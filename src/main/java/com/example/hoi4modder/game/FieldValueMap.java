package com.example.hoi4modder.game;

import java.util.Map;
import java.util.Set;

public class FieldValueMap<T> {
    private final Map<String, T> map;

    public FieldValueMap(Map<String, T> map) {
        this.map = map;
    }

    public void put(String field, T value) {
        map.put(field, value);
    }
    public T get(String field) {
        return map.get(field);
    }
    public void remove(String key) {
        map.remove(key);
    }
    public void remove(String key, T value) {
        map.remove(key, value);
    }

    public Set<String> fields() {
        return map.keySet();
    }
}

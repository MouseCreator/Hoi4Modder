package com.example.hoi4modder.game;

import java.util.Collection;
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

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public Set<String> keys() {
        return map.keySet();
    }

    public boolean containsValue(T value) {
        return map.containsValue(value);
    }

    public Collection<T> values() {
        return map.values();
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (!(other instanceof FieldValueMap<?>))
            return false;

        FieldValueMap<T> obj = (FieldValueMap<T>) other;
        return this.map.equals(obj.map);

    }
}

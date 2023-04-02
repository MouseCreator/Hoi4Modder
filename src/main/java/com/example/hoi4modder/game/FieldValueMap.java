package com.example.hoi4modder.game;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Map field (string) and object under that field
 * @param <T> class of values
 */
public class FieldValueMap<T> {
    private final Map<String, T> map;

    public FieldValueMap(Map<String, T> map) {
        this.map = map;
    }

    /**
     *
     * @param field - field to add
     * @param value - object to add under the field
     */
    public void put(String field, T value) {
        map.put(field, value);
    }

    /**
     *
     * @param field - map field
     * @return object under the field
     */
    public T get(String field) {
        return map.get(field);
    }

    /**
     * Removes key
     * @param key - key to remove
     */
    public void remove(String key) {
        map.remove(key);
    }

    /**
     *
     * @param key - key to remove
     * @param value - value to remove
     */
    public void remove(String key, T value) {
        map.remove(key, value);
    }

    /**
     *
     * @param field - field to check
     * @return true if the field is present
     */
    public boolean containsKey(String field) {
        return map.containsKey(field);
    }

    /**
     *
     * @return keySet for field map
     */
    public Set<String> keys() {
        return map.keySet();
    }

    /**
     *
     * @param value - value to check
     * @return true, if value is present
     */
    public boolean containsValue(T value) {
        return map.containsValue(value);
    }

    /**
     *
     * @return set of values
     */
    public Collection<T> values() {
        return map.values();
    }
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof FieldValueMap<?> obj))
            return false;
        return this.map.equals(obj.map);
    }
}

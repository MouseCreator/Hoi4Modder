package com.example.hoi4modder.model.files.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class DataPool<T> {
    private final Map<String, DataMap<T>> maps;

    public DataPool(Map<String, DataMap<T>> maps) {
        this.maps = maps;
    }

    public T get(String key) {
        for (String current : maps.keySet()) {
            T result = maps.get(current).get(key);
            if (result != null)
                return result;
        }
        throw new NoSuchElementException("Cannot find value " + key + " in the data pool");
    }

    public T put(String type, String key, T value) {
        DataMap<T> mapToAdd = maps.get(type);
        return mapToAdd.put(key, value);
    }

    public boolean createMapType(String type, String filename) {
        if (maps.containsKey(type)) {
            return false;
        }
        maps.put(type, new DataMap<>(new HashMap<>(), filename));
        return true;
    }
    public boolean remove(String key) {
        for (String current : maps.keySet()) {
            if(maps.get(current).remove(key))
                return true;
        }
        return false;
    }
    public boolean remove(String type, String key) {
        return maps.get(type).remove(key);
    }
    public boolean replace(String oldKey, String newKey) {
        for (String current : maps.keySet()) {
            if(maps.get(current).replaceKey(oldKey,newKey))
                return true;
        }
        return false;
    }

    public boolean replace(String type, String oldKey, String newKey) {
        return maps.get(type).replaceKey(oldKey, newKey);
    }

}

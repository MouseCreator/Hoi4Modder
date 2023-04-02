package com.example.hoi4modder.model.files.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Map of the maps. Contains data maps of shared type, loaded from different classes
 * @param <T> - class, data map is based on
 */
public class DataPool<T> {
    private final Map<String, DataMap<T>> maps;

    /**
     *
     * @return data pool with hash map of strings
     */
    public static DataPool<String> getHashStringPool() {
        return new DataPool<>(new HashMap<>());
    }
    public DataPool(Map<String, DataMap<T>> maps) {
        this.maps = maps;
    }

    /**
     *
     * @param key - key to get from the map
     * @return value under the key
     */
    public T get(String key) {
        for (String current : maps.keySet()) {
            T result = maps.get(current).get(key);
            if (result != null)
                return result;
        }
        throw new NoSuchElementException("Cannot find value " + key + " in the data pool");
    }

    /**
     *
     * @param key - key to find
     * @return true if the key is present in the map
     */
    public boolean containsKey(String key) {
        for (String current : maps.keySet()) {
            if(maps.get(current).containsKey(key))
                return true;
        }
        return false;
    }

    /**
     *
     * @param type - key of the map
     * @param map - map to add
     */
    public void addDataMap(String type, DataMap<T> map) {
        this.maps.put(type, map);
    }

    /**
     *
     * @param type - map to put key
     * @param key - key of the object
     * @param value - value to add under the key
     * @return previous value under the key
     */
    public T put(String type, String key, T value) {
        DataMap<T> mapToAdd = maps.get(type);
        return mapToAdd.put(key, value);
    }

    /**
     * Removes key from the map
     * @param key - key to remove
     * @return true if key was removed
     */
    public boolean remove(String key) {
        for (String current : maps.keySet()) {
            if(maps.get(current).remove(key))
                return true;
        }
        return false;
    }

    /**
     * Optimized version of the remove
     * @param type - map to look for key in
     * @param key - key to remove
     * @return true if the key was removed
     */
    public boolean remove(String type, String key) {
        return maps.get(type).remove(key);
    }

    /**
     * Replaces key and keeps object
     * @param oldKey - key to replace
     * @param newKey - key to insert
     * @return true if oldKey was found and replaced
     */
    public boolean replace(String oldKey, String newKey) {
        if (containsKey(newKey)) {
            throw new IllegalArgumentException("Map already contains " + newKey);
        }
        for (String current : maps.keySet()) {
            if(maps.get(current).replaceKey(oldKey,newKey))
                return true;
        }
        return false;
    }

    /**
     * Optimized version of the replacement. Has to be used, when type in known
     * @param type - map to look for oldKey in
     * @param oldKey - key to replace
     * @param newKey - key to insert
     * @return true, if key was found and replaced
     */
    public boolean replace(String type, String oldKey, String newKey) {
        if (maps.containsKey(newKey)) {
            throw new IllegalArgumentException("Map already contains " + newKey);
        }
        return maps.get(type).replaceKey(oldKey, newKey);
    }

}

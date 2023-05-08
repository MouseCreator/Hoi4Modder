package com.example.hoi4modder.model.files.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map of data, loaded from game or modification
 * @param <T> - data class, saved in the map
 */
public class DataMap<T> {
    private final Map<String, T> map;
    private boolean isReadOnly;

    /**
     *
     * @return new empty data map of strings, using hashmap
     */
    public static DataMap<String> getHashStringMap() {
        return new DataMap<>(new HashMap<>());
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    private String filename;

    /**
     *
     * @param map - map that has to be used by DataMap
     */

    public DataMap(Map<String, T> map) {
        this.map = map;
    }

    /**
     *
     * @param key - key of the instance in map
     * @return value under the specified key
     */
    public T get(String key) {
        return map.get(key);
    }

    /**
     *
     * @param key - key to put in the map
     * @param value - value under the key
     * @return previous value in the map
     */
    public T put(String key, T value) {
        if (isReadOnly)
            throw new UnsupportedOperationException("Cannot make changes in this map");
        return map.put(key, value);
    }

    /**
     *
     * @return file, from which the data map was created
     */
    public String getFilename() {
        return filename;
    }

    /**
     *
     * @param key - key to remove from the map
     * @return true, if value was removed
     */
    public boolean remove(String key) {
        if (isReadOnly)
            throw new UnsupportedOperationException("Cannot make changes in this map");
        if (map.containsKey(key)) {
            map.remove(key);
            return true;
        }
        return false;
    }

    /**
     * Changes key and keeps the value
     * @param oldKey - key to replace
     * @param newKey - key to insert
     * @return true, if key was replaced
     */
    public boolean replaceKey(String oldKey, String newKey) {
        if (isReadOnly)
            throw new UnsupportedOperationException("Cannot make changes in this map");
        if (map.containsKey(oldKey)) {
            T value = map.remove(oldKey);
            map.put(newKey, value);
            return true;
        }
        return false;
    }

    /**
     *
     * @param key - key to check
     * @return true, if contains the key
     */
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     *
     * @param value - value to check
     * @return true if contains the value
     */
    public boolean containsValue(T value) {
        return map.containsValue(value);
    }

    /**
     * If the map is readOnly, it cannot be changed
     * @return true, if file cannot be changed.
     */
    public boolean isReadOnly() {
        return isReadOnly;
    }

    /**
     * If true, it is allowed to only get values from map
     * @param readOnly - value to set
     */
    public void setReadOnly(boolean readOnly) {
        this.isReadOnly = readOnly;
    }

    /**
     *
     * @return key set of the map
     */
    public Set<String> getKeys() {
        return map.keySet();
    }

    public Map<String, T> getMap() {
        return map;
    }
}

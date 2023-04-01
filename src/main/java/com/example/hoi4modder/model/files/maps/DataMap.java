package com.example.hoi4modder.model.files.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataMap<T> {
    private final Map<String, T> map;
    private boolean isReadOnly;

    public static DataMap<String> getHashStringMap() {
        return new DataMap<>(new HashMap<>());
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    private String filename;

    public DataMap(Map<String, T> map) {
        this.map = map;
    }

    public T get(String key) {
        return map.get(key);
    }

    public T put(String key, T value) {
        if (isReadOnly)
            throw new UnsupportedOperationException("Cannot make changes in this map");
        return map.put(key, value);
    }

    public String getFilename() {
        return filename;
    }

    public boolean remove(String key) {
        if (isReadOnly)
            throw new UnsupportedOperationException("Cannot make changes in this map");
        if (map.containsKey(key)) {
            map.remove(key);
            return true;
        }
        return false;
    }

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
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }
    public boolean containsValue(String value) {
        return map.containsKey(value);
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.isReadOnly = readOnly;
    }

    public Set<String> getKeys() {
        return map.keySet();
    }
}

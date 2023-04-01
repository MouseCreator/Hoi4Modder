package com.example.hoi4modder.model.files.maps;

import java.util.Map;

public class DataMap<T> {
    private final Map<String, T> map;
    private boolean changeable;
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
        return map.put(key, value);
    }

    public String getFilename() {
        return filename;
    }

    public boolean remove(String key) {
        if (map.containsKey(key)) {
            map.remove(key);
            return true;
        }
        return false;
    }

    public boolean replaceKey(String oldKey, String newKey) {
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

    public boolean isChangeable() {
        return changeable;
    }

    public void setChangeable(boolean changeable) {
        this.changeable = changeable;
    }
}

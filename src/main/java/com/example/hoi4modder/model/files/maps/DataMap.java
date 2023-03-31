package com.example.hoi4modder.model.files.maps;

import java.util.Map;

public class DataMap {
    private final Map<String, String> map;

    private final String filename;

    public DataMap(Map<String, String> map, String filename) {
        this.map = map;
        this.filename = filename;
    }

    public String get(String key) {
        return map.get(key);
    }

    public String put(String key, String value) {
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
            String value = map.remove(oldKey);
            map.put(newKey, value);
            return true;
        }
        return false;
    }
}

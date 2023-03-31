package com.example.hoi4modder.model.files.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class DataPool {
    private final Map<String, DataMap> maps;

    public DataPool(Map<String, DataMap> maps) {
        this.maps = maps;
    }

    public String get(String key) {
        for (String current : maps.keySet()) {
            String result = maps.get(current).get(key);
            if (result != null)
                return result;
        }
        throw new NoSuchElementException("Cannot find value " + key + " in the data pool");
    }

    public String put(String type, String key, String value) {
        DataMap mapToAdd = maps.get(type);
        return mapToAdd.put(key, value);
    }

    public boolean createMapType(String type, String filename) {
        if (maps.containsKey(type)) {
            return false;
        }
        maps.put(type, new DataMap(new HashMap<>(), filename));
        return true;
    }
    public boolean remove(String key) {
        for (String current : maps.keySet()) {
            if(maps.get(current).remove(key))
                return true;
        }
        return false;
    }
    public boolean replace(String oldKey, String newKey) {
        for (String current : maps.keySet()) {
            if(maps.get(current).replaceKey(oldKey,newKey))
                return true;
        }
        return false;
    }

}

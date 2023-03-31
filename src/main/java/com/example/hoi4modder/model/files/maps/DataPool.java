package com.example.hoi4modder.model.files.maps;

import java.util.Map;
import java.util.NoSuchElementException;

public class DataPool {
    private Map<String, DataMap> maps;

    public String get(String key) {
        for (String current : maps.keySet()) {
            String result = maps.get(current).get(key);
            if (result != null)
                return result;
        }
        throw new NoSuchElementException("Cannot find value " + key + " in the data pool");
    }
}

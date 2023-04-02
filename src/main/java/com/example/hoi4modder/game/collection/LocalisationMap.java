package com.example.hoi4modder.game.collection;

import com.example.hoi4modder.model.files.maps.DataMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LocalisationMap {
    private final Map<String, String> map = new HashMap<>();

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }
    public String put(String key, String value) {
       return map.put(key, value);
    }
    public String get(String key) {
        return map.get(key);
    }

    public Set<String> keys() {
        return map.keySet();
    }

    public DataMap<String> toDataMap() {
        return new DataMap<>(map);
    }
}

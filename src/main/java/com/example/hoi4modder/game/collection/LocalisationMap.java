package com.example.hoi4modder.game.collection;

import java.util.Map;
import java.util.Set;

public class LocalisationMap {
    private Map<String, String> map;

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
}

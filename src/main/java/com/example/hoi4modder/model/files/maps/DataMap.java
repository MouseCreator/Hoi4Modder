package com.example.hoi4modder.model.files.maps;

import java.util.Map;

public class DataMap {
    private Map<String, String> map;

    public String get(String key) {
        return map.get(key);
    }
}

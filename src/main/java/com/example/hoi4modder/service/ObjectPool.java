package com.example.hoi4modder.service;

import java.util.HashMap;
import java.util.Map;

public class ObjectPool {
    private Map<String, Object> objectMap;

    public ObjectPool(HashMap<String,Object> map) {
        this.objectMap = map;
    }

    public static ObjectPool getHashObjectPool() {
        return new ObjectPool(new HashMap<>());
    }
    public Object put(String key, Object obj) {
        return objectMap.put(key, obj);
    }
    public Object get(String key) {
        return objectMap.get(key);
    }
    public Object remove(String key) {
        return objectMap.remove(key);
    }
}

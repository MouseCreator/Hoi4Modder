package com.example.hoi4modder.service;

import java.util.HashMap;
import java.util.Map;

/**
 * OBJECT POOL PATTERN
 * Pool of objects
 * Uses String as keys
 * No get operation support to avoid using service by several objects at a time
 */
public class ObjectPool {
    private final Map<String, Object> objectMap;

    public ObjectPool(HashMap<String,Object> map) {
        this.objectMap = map;
    }

    /**
     *
     * @return new empty object pool based on hash map
     */
    public static ObjectPool getHashObjectPool() {
        return new ObjectPool(new HashMap<>());
    }

    /**
     *
     * @param key - key for object
     * @param obj - object to put in the pool
     * @return previous object in the map
     */
    public Object put(String key, Object obj) {
        return objectMap.put(key, obj);
    }

    /**
     *
     * @param key - key of object to extract from the map
     * @return extracted object from map
     */
    public Object extract(String key) {
        return objectMap.remove(key);
    }
}

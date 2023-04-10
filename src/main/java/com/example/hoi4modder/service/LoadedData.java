package com.example.hoi4modder.service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LoadedData {
    private final Map<String, Object> objectMap;

    public LoadedData(Map<String,Object> map) {
        this.objectMap = map;
    }

    /**
     *
     * @return new empty object pool based on hash map
     */
    public static LoadedData getHashLoadedData() {
        return new LoadedData(new HashMap<>());
    }
    /**
     *
     * @return new empty object pool based on tree map
     */
    public static LoadedData getTreeLoadedData() {
        return new LoadedData(new TreeMap<>());
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
    public Object remove(String key) {
        return objectMap.remove(key);
    }

    /**
     *
     * @param key - key of object to get from the map
     * @return object from map if it is in the map
     */
    public Object get(String key) {
        return objectMap.get(key);
    }
}

package com.example.hoi4modder.model.files.maps;

import java.util.Map;

public class LoadedData<T> {
    private final Map<String, DataPool<T>> data;

    public LoadedData(Map<String, DataPool<T>> data) {
        this.data = data;
    }

    public DataPool<T> getDataPool(String name) {
        return data.get(name);
    }
    public DataPool<T> popDataPool(String name) {
        return data.remove(name);
    }
    public DataPool<T> insertDataPool(String name, DataPool<T> dataPool) {
        return data.put(name, dataPool);
    }
}

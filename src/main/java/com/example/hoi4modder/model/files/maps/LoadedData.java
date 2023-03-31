package com.example.hoi4modder.model.files.maps;

import java.util.HashMap;
public class LoadedData {

    private final DataPool<String> localisationPool = new DataPool<>(new HashMap<>());
    private final DataPool<String> graphicsPool = new DataPool<>(new HashMap<>());

    public DataPool<String> getLocalisationPool() {
        return localisationPool;
    }

    public DataPool<String> getGraphicsPool() {
        return graphicsPool;
    }
}

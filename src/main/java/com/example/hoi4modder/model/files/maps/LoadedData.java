package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.game.Trait;

import java.util.HashMap;
public class LoadedData {
    private static final LoadedData data = new LoadedData();

    public LoadedData getData() {
        return data;
    }

    private final DataPool<String> localisationPool = new DataPool<>(new HashMap<>());
    private final DataPool<String> graphicsPool = new DataPool<>(new HashMap<>());
    private final DataPool<Trait> traitsPool = new DataPool<>(new HashMap<>());

    public DataPool<String> getLocalisationPool() {
        return localisationPool;
    }

    public DataPool<String> getGraphicsPool() {
        return graphicsPool;
    }

    public DataPool<Trait> getTraitsPool() {
        return traitsPool;
    }
}

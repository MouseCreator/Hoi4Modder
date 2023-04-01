package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.service.Destinations;

import java.util.HashMap;

public class LoadedDataCreator {
    public LoadedData<String> getMainData() {
        LoadedData<String> mainData = new LoadedData<>(new HashMap<>());

        mainData.insertDataPool("localisation", createLocalisationPool());
        return mainData;
    }

    private DataPool<String> createLocalisationPool() {
        DataMap<String> dataMap = new DataMap<>(new HashMap<>(), "");


        DataPool<String> localisationPool = DataPool.getHashStringPool();
        localisationPool.createMapType("characters", Destinations.get().localisation("new-characters"));
        return localisationPool;
    }
}

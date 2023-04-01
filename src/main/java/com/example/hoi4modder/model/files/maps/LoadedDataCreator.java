package com.example.hoi4modder.model.files.maps;

import com.example.hoi4modder.model.files.manager.FileSearcher;
import com.example.hoi4modder.service.Destinations;

import java.util.HashMap;

public class LoadedDataCreator {
    public LoadedData<String> getMainData() {
        LoadedData<String> mainData = new LoadedData<>(new HashMap<>());

        mainData.insertDataPool("localisation", createLocalisationPool());
        return mainData;
    }

    private DataPool<String> createLocalisationPool() {

        FileSearcher fileSearcher = new FileSearcher(Destinations.get().localisation());

        DataPool<String> localisationPool = DataPool.getHashStringPool();

        return null;
    }
    private void addCharacterLocalisation(FileSearcher searcher, DataPool<String> localisationPool) {
        StringDataMapBuilder mapBuilder = new StringDataMapBuilder();
        mapBuilder.setFilename("");
    }
}

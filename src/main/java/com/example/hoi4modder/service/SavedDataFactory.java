package com.example.hoi4modder.service;

import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.maps.GraphicsBuilder;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.model.files.maps.LocalisationBuilder;
import com.example.hoi4modder.model.files.maps.PoolDirector;

public class SavedDataFactory {
    private final static SavedDataFactory factory = new SavedDataFactory();
    public static SavedDataFactory factory() {
        return factory;
    }
    public SavedData getSavedData() {
        FileSearchService fileSearchService = new FileSearchService();
        LoadedData loadedData = getLoadedData(fileSearchService);
        return new SavedData(fileSearchService, loadedData);
    }

    private LoadedData getLoadedData(FileSearchService service) {
        PoolDirector director = new PoolDirector();
        LoadedData loadedData = new LoadedData();
        director.setBuilder(new GraphicsBuilder());
        loadedData.setGraphicsData(director.makeDataPool(service));
        director.setBuilder(new LocalisationBuilder());
        loadedData.setLocalisationData(director.makeDataPool(service));
        return loadedData;
    }
}

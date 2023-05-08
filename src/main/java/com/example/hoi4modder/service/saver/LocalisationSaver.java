package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.model.files.iovisitor.Unparser;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.GameFilesWriter;
import com.example.hoi4modder.model.files.manager.strategy.PutCreateStrategy;
import com.example.hoi4modder.model.files.maps.DataMap;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.service.Destinations;
import com.example.hoi4modder.service.SavedDataContainer;


public class LocalisationSaver {
    private final DataPool<String> localisationData;
    public LocalisationSaver(DataPool<String> localisationData) {
        this.localisationData = localisationData;
    }

    public void save() {
        for (String key : localisationData.keys()) {
            DataMap<String> dataMap = localisationData.getMap(key);
            saveDataMap(dataMap);
        }
    }

    private void saveDataMap(DataMap<String> dataMap) {
        String filename = dataMap.getFilename();
        FileSearchService fileSearchService = SavedDataContainer.get().fileSearchService();
        fileSearchService.setStrategy(new PutCreateStrategy());
        fileSearchService.setDirectory(Destinations.get().localisation());
        String file = fileSearchService.findInstance(filename);

        GameFilesWriter writer = new GameFilesWriter();
        Unparser unparser = new Unparser();
        unparser.visitLocalisationMap(new LocalisationMap(dataMap));
        try {
            writer.write(file, unparser.getBlock().toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

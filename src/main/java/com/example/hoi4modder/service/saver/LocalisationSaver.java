package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.model.files.iovisitor.Unparser;
import com.example.hoi4modder.model.files.manager.GameFilesWriter;
import com.example.hoi4modder.model.files.maps.DataMap;
import com.example.hoi4modder.model.files.maps.DataPool;


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

        GameFilesWriter writer = new GameFilesWriter();
        Unparser unparser = new Unparser();
        unparser.visitLocalisationMap(new LocalisationMap(dataMap));
        try {
            writer.write(filename, unparser.getBlock().toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

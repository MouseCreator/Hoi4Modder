package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.model.files.iovisitor.Unparser;
import com.example.hoi4modder.model.files.manager.GameFilesWriter;
import com.example.hoi4modder.model.files.maps.DataMap;
import com.example.hoi4modder.model.files.maps.DataPool;

/**
 * Saver for localisation data
 */
public class LocalisationSaver {
    private final DataPool<String> localisationData;

    /**
     *
     * @param localisationData - data to be saved
     */
    public LocalisationSaver(DataPool<String> localisationData) {
        this.localisationData = localisationData;
    }

    /**
     * Saves localisation to file it was loaded from
     */
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

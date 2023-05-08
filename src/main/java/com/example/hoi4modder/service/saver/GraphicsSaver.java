package com.example.hoi4modder.service.saver;


import com.example.hoi4modder.game.SpriteType;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.model.files.iovisitor.Unparser;
import com.example.hoi4modder.model.files.manager.GameFilesWriter;
import com.example.hoi4modder.model.files.maps.DataMap;
import com.example.hoi4modder.model.files.maps.DataPool;

public class GraphicsSaver {
    private final DataPool<String> graphicsData;
    public GraphicsSaver(DataPool<String> graphicsData) {
        this.graphicsData = graphicsData;
    }

    public void save() {
        for (String key : graphicsData.keys()) {
            DataMap<String> dataMap = graphicsData.getMap(key);
            saveDataMap(dataMap);
        }
    }

    private void saveDataMap(DataMap<String> dataMap) {
        String filename = dataMap.getFilename();

        GameFilesWriter writer = new GameFilesWriter();
        Unparser unparser = new Unparser();
        unparser.visitSpriteList(buildSpriteList(dataMap));
        try {
            writer.write(filename, unparser.getBlock().toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private SpriteList buildSpriteList(DataMap<String> dataMap) {
        SpriteList spriteList = new SpriteList();
        for (String str : dataMap.getKeys()) {
            spriteList.add(new SpriteType(str, dataMap.get(str)));
        }
        return spriteList;
    }
}

package com.example.hoi4modder.service;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.SpriteType;
import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.model.files.StringToPropertyConvertor;
import com.example.hoi4modder.model.files.iovisitor.Parser;
import com.example.hoi4modder.model.files.manager.GameFilesReader;
import com.example.hoi4modder.model.files.maps.DataMap;

import java.io.IOException;

public class AbstractFactory {
    private final static AbstractFactory factory = new AbstractFactory();

    public static AbstractFactory get() {
        return factory;
    }

    public GameCharacterList getCharacterList(String fromFile) throws IOException {
        GameFilesReader reader = new GameFilesReader();
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        String characters = reader.read(fromFile);
        Parser parser = new Parser();
        parser.setBlock(convertor.forStructuredFile(characters));
        GameCharacterList list = GameCharacterList.getArrayList();
        parser.visitCharacterList(list);
        return list;
    }

    public DataMap<String> graphicsMap(String filename) throws IOException {
        GameFilesReader reader = new GameFilesReader();
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        String characters = reader.read(filename);
        Parser parser = new Parser();
        parser.setBlock(convertor.forStructuredFile(characters));
        SpriteList list = new SpriteList();
        parser.visitSpriteList(list);
        return spriteListToMap(list);
    }

    private DataMap<String> spriteListToMap(SpriteList list) {
        DataMap<String> map = DataMap.getHashStringMap();
        for (SpriteType spriteType : list) {
            map.put(spriteType.getName(), spriteType.getTextureFile());
        }
        return map;
    }

    public DataMap<String> localeMap(String filename) throws IOException {
        GameFilesReader reader = new GameFilesReader();
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        String localisation = reader.read(filename);
        Parser parser = new Parser();
        parser.setBlock(convertor.forStructuredFile(localisation));
        LocalisationMap localisationMap = new LocalisationMap();
        parser.visitLocalisationMap(localisationMap);
        return localisationMap.toDataMap();
    }
}

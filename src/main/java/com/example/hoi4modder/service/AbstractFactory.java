package com.example.hoi4modder.service;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.SpriteType;
import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.model.files.maps.DataMap;

import java.io.IOException;

public class AbstractFactory {

    private final ParserFacade parserFacade = new ParserFacade();
    private final static AbstractFactory factory = new AbstractFactory();

    public static AbstractFactory get() {
        return factory;
    }

    public GameCharacterList getCharacterList(String fromFile) throws IOException {
        return parserFacade.characterListFromFile(fromFile);
    }

    public DataMap<String> graphicsMap(String filename) throws IOException {
        SpriteList list = parserFacade.spriteListFromFile(filename);
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
        LocalisationMap localisationMap = parserFacade.localisationMapFromFile(filename);
        return localisationMap.toDataMap();
    }
}

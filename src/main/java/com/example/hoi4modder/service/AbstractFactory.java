package com.example.hoi4modder.service;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.SpriteType;
import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.model.files.maps.DataMap;

import java.io.IOException;

/**
 * ABSTRACT FACTORY PATTERN
 * SINGLETON PATTERN
 * Abstract factory for collections of game data.
 */
public class AbstractFactory {

    private final ParserFacade parserFacade = new ParserFacade();
    private final static AbstractFactory factory = new AbstractFactory();

    /**
     *
     * @return singleton instance of abstract factory
     */
    public static AbstractFactory get() {
        return factory;
    }

    /**
     * @param fromFile - full path to file to get characters from
     * @throws IOException if file was not found or an error occurred during reading a file
     */
    public void getCharacterList(GameCharacterList list, String fromFile) throws IOException {
        parserFacade.characterListFromFile(list, fromFile);
    }
    /**
     *
     * @param filename - full path to file to get sprite types from
     * @return list of textures, saved in file
     * @throws IOException if file was not found or an error occurred during reading a file
     */
    public DataMap<String> graphicsMap(String filename) throws IOException {
        SpriteList list = parserFacade.spriteListFromFile(filename);
        DataMap<String> dataMap = spriteListToMap(list);
        dataMap.setFilename(filename);
        return dataMap;
    }

    private DataMap<String> spriteListToMap(SpriteList list) {
        DataMap<String> map = DataMap.getHashStringMap();
        for (SpriteType spriteType : list) {
            map.put(spriteType.getName(), spriteType.getTextureFile());
        }
        return map;
    }
    /**
     *
     * @param filename - full path to localisation (.yml) file
     * @return localisation map, created from input file
     * @throws IOException if file was not found of an error occurred during reading a file
     */
    public DataMap<String> localeMap(String filename) throws IOException {
        LocalisationMap localisationMap = parserFacade.localisationMapFromFile(filename);
        localisationMap.setFileName(filename);
        return localisationMap.toDataMap();
    }
}

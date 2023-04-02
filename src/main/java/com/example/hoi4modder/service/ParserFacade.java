package com.example.hoi4modder.service;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.game.collection.Visitable;
import com.example.hoi4modder.model.files.StringToPropertyConvertor;
import com.example.hoi4modder.model.files.iovisitor.Parser;
import com.example.hoi4modder.model.files.manager.GameFilesReader;

import java.io.IOException;

/**
 * Facade class for loading data from game files by filename
 */
public class ParserFacade {
    /**
     *
     * @param filename - filename for character list. Should fit the in-game style and contain one "characters" block
     * @return list of characters in the file.
     * @throws IOException if file was not found or error occurred during reading a file
     */
    public GameCharacterList characterListFromFile(String filename) throws IOException {
        GameCharacterList list = GameCharacterList.getArrayList();
        parseVisitable(filename, list);
        return list;
    }

    private void parseVisitable(String filename, Visitable visitable) throws IOException {
        GameFilesReader reader = new GameFilesReader();
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        String characters = reader.read(filename);
        Parser parser = new Parser();
        parser.setBlock(convertor.forStructuredFile(characters));
        visitable.acceptVisitor(parser);
    }
    /**
     *
     * @param filename - filename for character list. Should fit the in-game style and contain one "SpriteTypes" block
     * @return list sprite types from the file
     * @throws IOException if file was not found or error occurred during reading a file
     */
    public SpriteList spriteListFromFile(String filename) throws IOException {
        SpriteList list = new SpriteList();
        parseVisitable(filename, list);
        return list;
    }
    /**
     *
     * @param filename - filename for character list. Expected .yml file with in-game style of key:version "value"
     * @return localisation map, created from input file. If it has two or more equal keys, keeps the last one.
     * @throws IOException if file was not found or error occurred during reading a file
     */
    public LocalisationMap localisationMapFromFile(String filename) throws IOException {
        LocalisationMap localisationMap = new LocalisationMap();
       parseVisitable(filename, localisationMap);
        return localisationMap;
    }
}

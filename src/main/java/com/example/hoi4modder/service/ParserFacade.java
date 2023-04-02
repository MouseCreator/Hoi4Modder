package com.example.hoi4modder.service;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.model.files.StringToPropertyConvertor;
import com.example.hoi4modder.model.files.iovisitor.Parser;
import com.example.hoi4modder.model.files.manager.GameFilesReader;

import java.io.IOException;

public class ParserFacade {
    public GameCharacterList characterListFromFile(String filename) throws IOException {
        GameFilesReader reader = new GameFilesReader();
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        String characters = reader.read(filename);
        Parser parser = new Parser();
        parser.setBlock(convertor.forStructuredFile(characters));
        GameCharacterList list = GameCharacterList.getArrayList();
        parser.visitCharacterList(list);
        return list;
    }

    public SpriteList spriteListFromFile(String filename) throws IOException {
        GameFilesReader reader = new GameFilesReader();
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        String characters = reader.read(filename);
        Parser parser = new Parser();
        parser.setBlock(convertor.forStructuredFile(characters));
        SpriteList list = new SpriteList();
        parser.visitSpriteList(list);
        return list;
    }

    public LocalisationMap localisationMapFromFile(String filename) throws IOException {
        GameFilesReader reader = new GameFilesReader();
        StringToPropertyConvertor convertor = new StringToPropertyConvertor();
        String localisation = reader.read(filename);
        Parser parser = new Parser();
        parser.setBlock(convertor.forStructuredFile(localisation));
        LocalisationMap localisationMap = new LocalisationMap();
        parser.visitLocalisationMap(localisationMap);
        return localisationMap;
    }
}

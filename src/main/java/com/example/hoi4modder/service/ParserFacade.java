package com.example.hoi4modder.service;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.game.collection.Visitable;
import com.example.hoi4modder.model.files.StringToPropertyConvertor;
import com.example.hoi4modder.model.files.iovisitor.Parser;
import com.example.hoi4modder.model.files.manager.GameFilesReader;

import java.io.IOException;

public class ParserFacade {
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

    public SpriteList spriteListFromFile(String filename) throws IOException {
        SpriteList list = new SpriteList();
        parseVisitable(filename, list);
        return list;
    }

    public LocalisationMap localisationMapFromFile(String filename) throws IOException {
        LocalisationMap localisationMap = new LocalisationMap();
       parseVisitable(filename, localisationMap);
        return localisationMap;
    }
}

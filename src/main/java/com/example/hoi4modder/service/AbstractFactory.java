package com.example.hoi4modder.service;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.StringToPropertyConvertor;
import com.example.hoi4modder.model.files.iovisitor.Parser;
import com.example.hoi4modder.model.files.manager.GameFilesReader;

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
}

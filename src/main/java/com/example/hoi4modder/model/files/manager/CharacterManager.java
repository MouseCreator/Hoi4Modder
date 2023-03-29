package com.example.hoi4modder.model.files.manager;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.StringToPropertyConvertor;
import com.example.hoi4modder.model.files.iovisitor.Parser;
import com.example.hoi4modder.service.Destinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CharacterManager {
    FileSearcher searcher = new FileSearcher();
    GameFilesReader reader = new GameFilesReader();
    Parser parser = new Parser();
    StringToPropertyConvertor convertor = new StringToPropertyConvertor();

    public GameCharacterList getCharactersForCountry(String countryTag) {
        try {
            String name = searcher.findCountryByTag(Destinations.get().characters(), countryTag);
            return getGameCharacterListFromFile(name);
        } catch (NoSuchElementException e) {
            //do something
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private GameCharacterList getGameCharacterListFromFile(String name) throws IOException {
        List<String> inputs = reader.readByLines(name);
        GameCharacterList resultList = new GameCharacterList(new ArrayList<>());
        parser.visitCharacterList(resultList, convertor.forStructuredFile(inputs));
        return resultList;
    }
}

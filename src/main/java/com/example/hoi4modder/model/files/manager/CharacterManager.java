package com.example.hoi4modder.model.files.manager;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.StringToPropertyConvertor;
import com.example.hoi4modder.model.files.iovisitor.Parser;
import com.example.hoi4modder.model.files.iovisitor.Unparser;
import com.example.hoi4modder.service.Destinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CharacterManager {
    FileSearcher searcher = new FileSearcher(Destinations.get().characters());
    GameFilesReader reader = new GameFilesReader();
    GameFilesWriter writer = new GameFilesWriter();
    StringToPropertyConvertor convertor = new StringToPropertyConvertor();

    public GameCharacterList getCharactersForCountry(String countryTag) {
        try {
            String name = searcher.findCountryByTag(countryTag);
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
        Parser parser = new Parser();
        GameCharacterList resultList = new GameCharacterList(new ArrayList<>());
        parser.setBlock(convertor.forStructuredFile(inputs));
        parser.visitCharacterList(resultList);
        return resultList;
    }

    public void setCharactersForCountry(String countryTag, GameCharacterList characters) {
        try {
            String name = searcher.findCountryByTag(countryTag);
            String message = charactersToFile(characters);
            writer.write(name, message);
        } catch (NoSuchElementException e) {
            //do something
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String charactersToFile(GameCharacterList characters) {
        Unparser unparser = new Unparser();
        unparser.visitCharacterList(characters);
        return unparser.getBlock().toFile();
    }
}

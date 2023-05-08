package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.iovisitor.Unparser;
import com.example.hoi4modder.model.files.manager.FileSearch;
import com.example.hoi4modder.model.files.manager.GameFilesReader;
import com.example.hoi4modder.model.files.manager.GameFilesWriter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to save character lists by tag
 */
public class CharacterFileSaver implements SimpleSaver {
    private final String countryTag;
    private final GameCharacterList gameCharacters;
    public CharacterFileSaver(String tag, GameCharacterList gameCharacters) {
        this.gameCharacters = gameCharacters;
        this.countryTag = tag;
    }

    /**
     * Saves characters to characters file of the country
     */
    public void save() {
        FileSearch fileSearchService = FileSearch.createPutReplaceService();
        String countryFile = fileSearchService.findCountryByTag(countryTag);
        writeOutput(countryFile);
        changeCountryFile(fileSearchService);
    }
    private void writeOutput(String countryFile) {
        GameFilesWriter writer = new GameFilesWriter();
        try {
            writer.write(countryFile, getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getMessage() {
        Unparser unparser = new Unparser();
        unparser.visitCharacterList(gameCharacters);
        return unparser.getBlock().toFile();
    }
    private void changeCountryFile(FileSearch fileSearch) {
        String countryFile = fileSearch.findCountryFile(countryTag);
        List<String> strings = processRecruitString(countryFile);

        for (GameCharacter character : gameCharacters) {
            strings.add("recruit_character = " + character.getIdentification());
        }

        toCountryFile(strings, countryFile);
    }

    private void toCountryFile(List<String> strings, String countryFile) {
        StringBuilder builder = new StringBuilder();

        for (String s : strings) {
            builder.append(s).append("\n");
        }

        GameFilesWriter writer = new GameFilesWriter();

        try {
            writer.write(countryFile, builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<String> processRecruitString(String countryFile) {
        GameFilesReader reader = new GameFilesReader();
        try {
            List<String> strings = reader.readByLines(countryFile);
            List<String> result = new LinkedList<>(strings);
            for (String s : strings) {
                if (!s.contains("recruit_character")) {
                    result.add(s);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

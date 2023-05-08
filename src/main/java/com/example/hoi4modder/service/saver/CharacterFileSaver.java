package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.iovisitor.Unparser;
import com.example.hoi4modder.model.files.manager.FileSearch;
import com.example.hoi4modder.model.files.manager.GameFilesWriter;

import java.io.IOException;

/**
 * Class to save character lists by tag
 */
public class CharacterFileSaver {
    private final String countryTag;
    private final GameCharacterList gameCharacters;
    public CharacterFileSaver(String tag, GameCharacterList gameCharacters) {
        this.gameCharacters = gameCharacters;
        this.countryTag = tag;
    }


    public void save() {
        FileSearch fileSearchService = FileSearch.createPutReplaceService();
        String countryFile = fileSearchService.findCountryByTag(countryTag);
        writeOutput(countryFile);
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

    private String generateRecruitCharacterMessage() {
        StringBuilder builder = new StringBuilder();

        for (GameCharacter character : gameCharacters) {
            builder.append("recruit_character = ") .append(character.getIdentification()).append("\n");
        }

        return builder.toString();
    }
}

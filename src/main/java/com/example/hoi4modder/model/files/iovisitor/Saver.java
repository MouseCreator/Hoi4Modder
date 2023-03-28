package com.example.hoi4modder.model.files.iovisitor;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;
import java.util.Map;

public class Saver implements Visitor {
    @Override
    public void visitCharacterList(GameCharacterList characterList) {
        Property mainBlock = new BlockProperty(); //readFile();

        PropertyCollection charactersCollection = mainBlock.getAll();

        for(Property property : charactersCollection) {
            characterList.getCharacterList().add(propertyToCharacter(property));
        }
    }

    private GameCharacter propertyToCharacter(Property property) {
        GameCharacter currentCharacter = new GameCharacter();
        currentCharacter.setIdentification(property.name());
        currentCharacter.setName(property.get("name").get(0).value());
        PropertyCollection portraits = property.get("portraits").get(0).getAll();
        createPortraits(currentCharacter, portraits);
        return currentCharacter;
    }

    private void createPortraits(GameCharacter currentCharacter, PropertyCollection portraits) {
        Map<String, String> characterPortraits = currentCharacter.getPortraits();
        for (Property portraitProperty : portraits) {
            characterPortraits.put(portraitProperty.name(), portraitProperty.value());
        }
        currentCharacter.setPortraits(characterPortraits);
    }
}

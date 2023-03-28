package com.example.hoi4modder.model.files.iovisitor;
import com.example.hoi4modder.game.*;
import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

public class Saver implements Visitor {
    @Override
    public void visitCharacterList(GameCharacterList characterList) {
        Property mainBlock = new BlockProperty();// readFile();

        PropertyCollection charactersCollection = mainBlock.getAll();

        for(Property property : charactersCollection) {
            characterList.getCharacterList().add(propertyToCharacter(property));
        }
    }

    private GameCharacter propertyToCharacter(Property property) {
        GameCharacter currentCharacter = new GameCharacter();
        currentCharacter.setIdentification(property.name());
        currentCharacter.setName(property.getFirst("name").value());
        PropertyCollection portraits = property.getFirst ("portraits").getAll();
        createPortraits(currentCharacter, portraits);
        addRoles(currentCharacter, property);
        return currentCharacter;
    }

    private void addRoles(GameCharacter currentCharacter, Property mainProperty) {
        FieldValueMap<CharacterRole> roles = currentCharacter.getRoles();
        Property advisor = mainProperty.getFirst("advisor");
        if (advisor != null) {
            Advisor advisorRole = new Advisor();
            advisorRole.setSlot(advisor.getFirst("slot").value());
            advisorRole.setToken(advisor.getFirst("idea_token").value());
            setTraitsForAdvisor(advisorRole, advisor);
        }
        Property coprsCommander = mainProperty.getFirst("corps_commander");
        if (coprsCommander != null) {
            UnitLeader commanderRole = new UnitLeader();
        }
        currentCharacter.setRoles(roles);
    }

    private void addAdvisor() {

    }
    private void setTraitsForAdvisor(Advisor advisor, Property role) {
        Property traitScope = role.getFirst("traits");
        if (traitScope != null) {
            for (Property property : traitScope.getAll()) {
                advisor.getTraits().add(property.value());
            }
        }
    }
    private void createPortraits(GameCharacter currentCharacter, PropertyCollection portraits) {
        FieldValueMap<String> characterPortraits = currentCharacter.getPortraits();
        for (Property portraitProperty : portraits) {
            characterPortraits.put(portraitProperty.name(), portraitProperty.value());
        }
        currentCharacter.setPortraits(characterPortraits);
    }
}

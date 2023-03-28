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
        Property advisorProperty = mainProperty.getFirst("advisor");
        if (advisorProperty != null) {
            Advisor advisorRole = Advisor.createAdvisor();
            advisorRole.setSlot(advisorProperty.getFirst("slot").value());
            advisorRole.setToken(advisorProperty.getFirst("idea_token").value());
            Property costProperty = advisorProperty.getFirst("cost");
            if (costProperty != null) {
                advisorRole.setCost(Integer.parseInt(costProperty.value()));
            }
            setTraitsForAdvisor(advisorRole, advisorProperty);
        }
        Property commanderProperty = mainProperty.getFirst("corps_commander");
        if (commanderProperty != null) {
            UnitLeader unitLeader = UnitLeader.getCorpsCommander();
            unitLeader.setSkill(Integer.parseInt(commanderProperty.getFirst("skill").value()));
            unitLeader.setAttackSkill(Integer.parseInt(commanderProperty.getFirst("attack_skill").value()));
            unitLeader.setDefenceSkill(Integer.parseInt(commanderProperty.getFirst("defense_skill").value()));
            unitLeader.setPlanningSkill(Integer.parseInt(commanderProperty.getFirst("planning_skill").value()));
            unitLeader.setLogisticsSkill(Integer.parseInt(commanderProperty.getFirst("logistics_skill").value()));
            setTraitsForAdvisor(unitLeader, commanderProperty);
        }
        currentCharacter.setRoles(roles);
    }

    private void setTraitsForAdvisor(CharacterRole role, Property roleProperty) {
        Property traitScope = roleProperty.getFirst("traits");
        if (traitScope != null) {
            for (Property property : traitScope.getAll()) {
                role.getTraits().add(property.value());
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

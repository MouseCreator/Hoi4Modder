package com.example.hoi4modder.model.files.iovisitor;
import com.example.hoi4modder.game.*;
import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

import java.util.HashMap;

public class Parser implements Visitor{
    public void visitCharacterList(GameCharacterList characterList, Property mainBlock) {
        PropertyCollection charactersCollection = mainBlock.getAll();
        for(Property property : charactersCollection) {
            GameCharacter character = propertyToCharacter(property);
            characterList.add(character);
        }
    }

    private GameCharacter propertyToCharacter(Property property) {
        GameCharacter currentCharacter = new GameCharacter();
        currentCharacter.setIdentification(property.name());
        currentCharacter.setName(property.getFirst("name").value());
        PropertyCollection portraits = property.getFirst("portraits").getAll();
        createPortraits(currentCharacter, portraits);
        addRoles(currentCharacter, property);
        return currentCharacter;
    }

    private void addRoles(GameCharacter currentCharacter, Property mainProperty) {
        FieldValueMap<CharacterRole> roles = new FieldValueMap<>(new HashMap<>());
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
            roles.put("advisor", advisorRole);
        }
        Property commanderProperty = mainProperty.getFirst("corps_commander");
        if (commanderProperty != null) {
            UnitLeader unitLeader = UnitLeader.getCorpsCommander();
            initializeUnitLeader(commanderProperty, unitLeader);
            roles.put("corps_commander", unitLeader);
        }

        Property fieldMarshalProperty = mainProperty.getFirst("field_marshal");
        if (fieldMarshalProperty != null) {
            UnitLeader unitLeader = UnitLeader.getFieldMarshal();
            initializeUnitLeader(fieldMarshalProperty, unitLeader);
            roles.put("field_marshal", unitLeader);
        }

        Property countryLeaderProperty = mainProperty.getFirst("country_leader");
        if (countryLeaderProperty != null) {
            CountryLeader countryLeader = CountryLeader.getCountryLeader();
            countryLeader.setIdeology(countryLeaderProperty.getFirst("ideology").value());
            setTraitsForAdvisor(countryLeader, countryLeaderProperty);
            roles.put("country_leader", countryLeader);
        }

        Property navyLeaderProperty = mainProperty.getFirst("navy_leader");
        if (navyLeaderProperty != null) {
            NavyLeader navyLeader = NavyLeader.getNavyLeader();
            navyLeader.setSkill(Integer.parseInt(navyLeaderProperty.getFirst("skill").value()));
            navyLeader.setAttackSkill(Integer.parseInt(navyLeaderProperty.getFirst("attack_skill").value()));
            navyLeader.setDefenceSkill(Integer.parseInt(navyLeaderProperty.getFirst("defense_skill").value()));
            navyLeader.setManeuveringSkill(Integer.parseInt(navyLeaderProperty.getFirst("maneuvering_skill").value()));
            navyLeader.setCoordinationSkill(Integer.parseInt(navyLeaderProperty.getFirst("coordination_skill").value()));
            setTraitsForAdvisor(navyLeader, navyLeaderProperty);
            roles.put("navy_leader", navyLeader);
        }
        currentCharacter.setRoles(roles);
    }

    private void initializeUnitLeader(Property commanderProperty, UnitLeader unitLeader) {
        unitLeader.setSkill(Integer.parseInt(commanderProperty.getFirst("skill").value()));
        unitLeader.setAttackSkill(Integer.parseInt(commanderProperty.getFirst("attack_skill").value()));
        unitLeader.setDefenceSkill(Integer.parseInt(commanderProperty.getFirst("defense_skill").value()));
        unitLeader.setPlanningSkill(Integer.parseInt(commanderProperty.getFirst("planning_skill").value()));
        unitLeader.setLogisticsSkill(Integer.parseInt(commanderProperty.getFirst("logistics_skill").value()));
        setTraitsForAdvisor(unitLeader, commanderProperty);
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
        FieldValueMap<String> characterPortraits = new FieldValueMap<>(new HashMap<>());
        for (Property portraitProperty : portraits) {
            for (Property categoryProperty : portraitProperty.getAll())
                characterPortraits.put(categoryProperty.name(), categoryProperty.value());
        }
        currentCharacter.setPortraits(characterPortraits);
    }
}

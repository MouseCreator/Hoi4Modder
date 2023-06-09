package com.example.hoi4modder.model.files.iovisitor;
import com.example.hoi4modder.game.*;
import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.game.roles.*;
import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;

import java.util.HashMap;

/**
 * Parses string to property
 */
public class Parser implements Visitor{

    private Property baseProperty;
    @Override
    public void visitCharacterList(GameCharacterList characterList) {
        PropertyCollection charactersCollection = baseProperty.getAll();
        for(Property property : charactersCollection) {
            GameCharacter character = propertyToCharacter(property);
            characterList.add(character);
        }
    }

    @Override
    public void visitNavyLeader(NavyLeader navyLeader) {
        navyLeader.setSkill(Integer.parseInt(baseProperty.getFirst("skill").value()));
        navyLeader.setAttackSkill(Integer.parseInt(baseProperty.getFirst("attack_skill").value()));
        navyLeader.setDefenceSkill(Integer.parseInt(baseProperty.getFirst("defense_skill").value()));
        navyLeader.setManeuveringSkill(Integer.parseInt(baseProperty.getFirst("maneuvering_skill").value()));
        navyLeader.setCoordinationSkill(Integer.parseInt(baseProperty.getFirst("coordination_skill").value()));
        setTraitsForAdvisor(navyLeader, baseProperty);
    }

    @Override
    public void visitCountryLeader(CountryLeader countryLeader) {
        countryLeader.setIdeology(baseProperty.getFirst("ideology").value());
        setTraitsForAdvisor(countryLeader, baseProperty);
    }

    @Override
    public void visitUnitLeader(UnitLeader unitLeader) {
        unitLeader.setSkill(Integer.parseInt(baseProperty.getFirst("skill").value()));
        unitLeader.setAttackSkill(Integer.parseInt(baseProperty.getFirst("attack_skill").value()));
        unitLeader.setDefenceSkill(Integer.parseInt(baseProperty.getFirst("defense_skill").value()));
        unitLeader.setPlanningSkill(Integer.parseInt(baseProperty.getFirst("planning_skill").value()));
        unitLeader.setLogisticsSkill(Integer.parseInt(baseProperty.getFirst("logistics_skill").value()));
        setTraitsForAdvisor(unitLeader, baseProperty);
    }

    @Override
    public void visitAdvisor(Advisor advisor) {
        advisor.setSlot(baseProperty.getFirst("slot").value());
        advisor.setToken(baseProperty.getFirst("idea_token").value());
        Property costProperty = baseProperty.getFirst("cost");
        if (costProperty != null) {
            advisor.setCost(Integer.parseInt(costProperty.value()));
        }
        setTraitsForAdvisor(advisor, baseProperty);
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
        Parser parser = new Parser();
        Property advisorProperty = mainProperty.getFirst("advisor");
        if (advisorProperty != null) {
            Advisor advisor = Advisor.createAdvisor();
            parser.setBlock(advisorProperty);
            parser.visitAdvisor(advisor);
            roles.put(CharacterRoles.ADVISOR, advisor);
        }

        Property commanderProperty = mainProperty.getFirst("corps_commander");
        if (commanderProperty != null) {
            UnitLeader unitLeader = UnitLeader.createCorpsCommander();
            parser.setBlock(commanderProperty);
            parser.visitUnitLeader(unitLeader);
            roles.put(CharacterRoles.UNIT_LEADER, unitLeader);
        }

        Property fieldMarshalProperty = mainProperty.getFirst("field_marshal");
        if (fieldMarshalProperty != null) {
            UnitLeader unitLeader = UnitLeader.createFieldMarshal();
            parser.setBlock(fieldMarshalProperty);
            parser.visitUnitLeader(unitLeader);
            roles.put(CharacterRoles.UNIT_LEADER, unitLeader);
        }

        Property countryLeaderProperty = mainProperty.getFirst("country_leader");
        if (countryLeaderProperty != null) {
            CountryLeader countryLeader = CountryLeader.createCountryLeader();
            parser.setBlock(countryLeaderProperty);
            countryLeader.acceptVisitor(parser);
            roles.put(CharacterRoles.COUNTRY_LEADER, countryLeader);
        }

        Property navyLeaderProperty = mainProperty.getFirst("navy_leader");
        if (navyLeaderProperty != null) {
            NavyLeader navyLeader = NavyLeader.createNavyLeader();
            parser.setBlock(navyLeaderProperty);
            navyLeader.acceptVisitor(parser);
            roles.put(CharacterRoles.NAVY_LEADER, navyLeader);
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
        FieldValueMap<String> characterPortraits = new FieldValueMap<>(new HashMap<>());
        for (Property portraitProperty : portraits) {
            for (Property categoryProperty : portraitProperty.getAll())
                characterPortraits.put(categoryProperty.name(), noQuotes(categoryProperty.value()));
        }
        currentCharacter.setPortraits(characterPortraits);
    }
    @Override
    public Property getBlock() {
        return baseProperty;
    }
    @Override
    public void setBlock(Property baseProperty) {
        this.baseProperty = baseProperty;
    }

    private String noQuotes(String origin) {
        if (!origin.contains("\""))
            return origin;
        int from = origin.indexOf('"') + 1;
        int to = origin.lastIndexOf('"');
        return origin.substring(from, to);
    }
    @Override
    public void visitSpriteList(SpriteList spriteList) {
        PropertyCollection spriteCollection = baseProperty.getAll();
        for(Property property : spriteCollection) {
            SpriteType sprite = toSpriteType(property);
            spriteList.add(sprite);
        }
    }

    @Override
    public void visitLocalisationMap(LocalisationMap localisationMap) {
        PropertyCollection list = baseProperty.getAll();
        for (Property property : list) {
            localisationMap.put(property.name(), noQuotes(property.value()));
        }
    }

    public SpriteType toSpriteType(Property property) {
        SpriteType spriteType = new SpriteType();
        spriteType.setName(noQuotes(property.getFirst("name").value()));
        spriteType.setTextureFile(noQuotes(property.getFirst("texturefile").value()));
        return spriteType;
    }



}

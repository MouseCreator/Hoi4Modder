package com.example.hoi4modder.model.files.iovisitor;

import com.example.hoi4modder.game.*;
import com.example.hoi4modder.game.roles.*;
import com.example.hoi4modder.model.files.properties.*;

public class Unparser implements Visitor {

    @Override
    public void visitCharacterList(GameCharacterList characterList, Property mainBlock) {
        for (GameCharacter character : characterList) {
            mainBlock.add(propertyFromCharacter(character));
        }
    }

    @Override
    public void visitNavyLeader(NavyLeader navyLeader, Property baseProperty) {
        baseProperty.add(createTraitsBlock(navyLeader));
        baseProperty.add(new FieldValueProperty("skill", String.valueOf(navyLeader.getSkill())));
        baseProperty.add(new FieldValueProperty("attack_skill", String.valueOf(navyLeader.getAttackSkill())));
        baseProperty.add(new FieldValueProperty("defense_skill", String.valueOf(navyLeader.getDefenceSkill())));
        baseProperty.add(new FieldValueProperty("maneuvering_skill", String.valueOf(navyLeader.getManeuveringSkill())));
        baseProperty.add(new FieldValueProperty("coordination_skill", String.valueOf(navyLeader.getCoordinationSkill())));
        baseProperty.add(new FieldValueProperty("legacy_id", String.valueOf(-1)));
    }
    private String inQuotes(String origin) {
        return "\"" + origin + "\"";
    }
    @Override
    public void visitCountryLeader(CountryLeader countryLeader, Property baseProperty) {
        baseProperty.add(new FieldValueProperty("ideology", String.valueOf(countryLeader.getIdeology())));
        baseProperty.add(createTraitsBlock(countryLeader));
        baseProperty.add(new FieldValueProperty("expire", inQuotes("1955.1.1.1")));
        baseProperty.add(new FieldValueProperty("id", String.valueOf(-1)));
    }
    private BlockProperty createTraitsBlock(CharacterRole role) {
        BlockProperty traitsBlock = new BlockProperty("traits");
        for (String s : role.getTraits()) {
            traitsBlock.add(new SingleValue(s));
        }
        return traitsBlock;
    }
    @Override
    public void visitUnitLeader(UnitLeader unitLeader, Property property) {

    }

    @Override
    public void visitAdvisor(Advisor advisor, BlockProperty property) {

    }

    private Property propertyFromCharacter(GameCharacter character) {
        BlockProperty blockProperty = new BlockProperty();
        blockProperty.setKey(character.getIdentification());
        blockProperty.add(new FieldValueProperty("name", character.getName()));
        blockProperty.add(addPortraitProperty(character.getPortraits()));
        addRoles(character, blockProperty);
        return null;
    }

    private void addRoles(GameCharacter character, BlockProperty mainProperty) {
        for (CharacterRole role : character.getRoles()) {
            Property toAdd = new BlockProperty();
            role.acceptVisitor(new Unparser());
        }
    }

    private BlockProperty addPortraitProperty(FieldValueMap<String> portraits) {
        BlockProperty mainBlock = new BlockProperty();
        mainBlock.setKey("portraits");
        BlockProperty civilianBlock = new BlockProperty();
        civilianBlock.setKey("civilian");
        for (String key : portraits.fields()) {
            civilianBlock.add(new FieldValueProperty(key, portraits.get(key)));
        }
        return mainBlock;
    }
}

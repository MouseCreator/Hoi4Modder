package com.example.hoi4modder.model.files.iovisitor;

import com.example.hoi4modder.game.*;
import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.game.roles.*;
import com.example.hoi4modder.model.files.properties.*;

public class Unparser implements Visitor {
    private Property baseProperty = new BlockProperty();
    @Override
    public void visitCharacterList(GameCharacterList characterList) {
        this.baseProperty = new BlockProperty("characters");
        for (GameCharacter character : characterList) {
            baseProperty.add(propertyFromCharacter(character));
        }
    }

    @Override
    public void visitNavyLeader(NavyLeader navyLeader) {
        this.baseProperty = new BlockProperty(navyLeader.getTitle());
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
    public void visitCountryLeader(CountryLeader countryLeader) {
        this.baseProperty = new BlockProperty(countryLeader.getTitle());
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
    public void visitUnitLeader(UnitLeader unitLeader) {
        this.baseProperty = new BlockProperty(unitLeader.getTitle());
        baseProperty.add(createTraitsBlock(unitLeader));
        baseProperty.add(new FieldValueProperty("skill", String.valueOf(unitLeader.getSkill())));
        baseProperty.add(new FieldValueProperty("attack_skill", String.valueOf(unitLeader.getAttackSkill())));
        baseProperty.add(new FieldValueProperty("defense_skill", String.valueOf(unitLeader.getDefenceSkill())));
        baseProperty.add(new FieldValueProperty("maneuvering_skill", String.valueOf(unitLeader.getLogisticsSkill())));
        baseProperty.add(new FieldValueProperty("coordination_skill", String.valueOf(unitLeader.getLogisticsSkill())));
        baseProperty.add(new FieldValueProperty("legacy_id", String.valueOf(-1)));
    }

    @Override
    public void visitAdvisor(Advisor advisor) {
        this.baseProperty = new BlockProperty("advisor");
        baseProperty.add(new FieldValueProperty("slot", String.valueOf(advisor.getSlot())));
        baseProperty.add(new FieldValueProperty("idea_token", String.valueOf(advisor.getToken())));
        baseProperty.add(simpleBlock("allowed",
                new FieldValueProperty("original_tag", advisor.getTitle().substring(0, 3))));
        baseProperty.add(createTraitsBlock(advisor));
        baseProperty.add(simpleBlock("ai_will_do", new FieldValueProperty("factor", "1")));
    }

    @Override
    public Property getBlock() {
        return this.baseProperty;
    }

    @Override
    public void setBlock(Property mainBlock) {
        this.baseProperty = mainBlock;
    }

    private BlockProperty simpleBlock(String name, Property value) {
        BlockProperty property = new BlockProperty(name);
        property.add(value);
        return property;
    }

    private Property propertyFromCharacter(GameCharacter character) {
        BlockProperty blockProperty = new BlockProperty();
        blockProperty.setKey(character.getIdentification());
        blockProperty.add(new FieldValueProperty("name", character.getName()));
        blockProperty.add(addPortraitProperty(character.getPortraits()));
        addRoles(character, blockProperty);
        return blockProperty;
    }

    private void addRoles(GameCharacter character, BlockProperty mainProperty) {
        for (CharacterRole role : character.getRoles()) {
            Unparser unparser = new Unparser();
            role.acceptVisitor(unparser);
            mainProperty.add(unparser.getBlock());
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
        mainBlock.add(civilianBlock);
        return mainBlock;
    }

    @Override
    public void visitSpriteList(SpriteList spriteList) {
        this.baseProperty = new BlockProperty("spriteTypes");
        for (SpriteType sprite : spriteList) {
            baseProperty.add(fromSpriteType(sprite));
        }
    }

    @Override
    public void visitLocalisationMap(LocalisationMap localisationMap) {
        this.baseProperty = new LocalisationBlock("l_russian");
        for (String key : localisationMap.keys()) {
            baseProperty.add(new LocalisationProperty(key, inQuotes(localisationMap.get(key))));
        }
    }

    private BlockProperty fromSpriteType(SpriteType spriteType) {
        BlockProperty property = new BlockProperty();
        property.setKey("SpriteType");
        property.add(new FieldValueProperty("name", inQuotes(spriteType.getName())));
        property.add(new FieldValueProperty("texturefile", inQuotes(spriteType.getTextureFile())));
        return property;
    }
}

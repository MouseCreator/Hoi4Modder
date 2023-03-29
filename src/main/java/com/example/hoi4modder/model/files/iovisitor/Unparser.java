package com.example.hoi4modder.model.files.iovisitor;

import com.example.hoi4modder.game.*;
import com.example.hoi4modder.game.roles.*;
import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.FieldValueProperty;
import com.example.hoi4modder.model.files.properties.Property;

public class Unparser implements Visitor {

    @Override
    public void visitCharacterList(GameCharacterList characterList, Property mainBlock) {
       // for (GameCharacter character : characterList) {
          //  mainBlock.add(propertyFromCharacter(character));
       // }
    }

    @Override
    public void visitNavyLeader(NavyLeader navyLeader, Property baseProperty) {

    }

    @Override
    public void visitCountryLeader(CountryLeader countryLeader, Property property) {

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

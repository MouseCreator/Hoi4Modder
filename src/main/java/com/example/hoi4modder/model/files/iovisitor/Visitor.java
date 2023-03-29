package com.example.hoi4modder.model.files.iovisitor;


import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.roles.Advisor;
import com.example.hoi4modder.game.roles.CountryLeader;
import com.example.hoi4modder.game.roles.NavyLeader;
import com.example.hoi4modder.game.roles.UnitLeader;
import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.Property;

public interface Visitor {
    void visitCharacterList(GameCharacterList characterList, Property baseProperty);

    void visitNavyLeader(NavyLeader navyLeader, Property baseProperty);

    void visitCountryLeader(CountryLeader countryLeader, Property property);

    void visitUnitLeader(UnitLeader unitLeader, Property property);

    void visitAdvisor(Advisor advisor, BlockProperty property);
}

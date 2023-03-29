package com.example.hoi4modder.model.files.iovisitor;


import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.roles.Advisor;
import com.example.hoi4modder.game.roles.CountryLeader;
import com.example.hoi4modder.game.roles.NavyLeader;
import com.example.hoi4modder.game.roles.UnitLeader;
import com.example.hoi4modder.model.files.properties.Block;
import com.example.hoi4modder.model.files.properties.Property;

public interface Visitor {
    void visitCharacterList(GameCharacterList characterList);

    void visitNavyLeader(NavyLeader navyLeader);

    void visitCountryLeader(CountryLeader countryLeader);

    void visitUnitLeader(UnitLeader unitLeader);

    void visitAdvisor(Advisor advisor);

    Property getBlock();
    void setBlock(Property mainBlock);
}

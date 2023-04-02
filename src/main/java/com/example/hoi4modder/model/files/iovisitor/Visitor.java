package com.example.hoi4modder.model.files.iovisitor;


import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.game.collection.LocalisationMap;
import com.example.hoi4modder.game.collection.SpriteList;
import com.example.hoi4modder.game.roles.Advisor;
import com.example.hoi4modder.game.roles.CountryLeader;
import com.example.hoi4modder.game.roles.NavyLeader;
import com.example.hoi4modder.game.roles.UnitLeader;
import com.example.hoi4modder.model.files.properties.Property;

/**
 * VISITOR.
 * Visitor for structures, stored as properties
 */
public interface Visitor {
    /**
     * Visits character List
     * @param characterList - list to visit
     */
    void visitCharacterList(GameCharacterList characterList);
    /**
     * Visits navy leader
     * @param navyLeader - structure visit
     */
    void visitNavyLeader(NavyLeader navyLeader);
    /**
     * Visits country leader
     * @param countryLeader - structure visit
     */
    void visitCountryLeader(CountryLeader countryLeader);
    /**
     * Visits unit leader: corps commander of field marshal
     * @param unitLeader - structure visit
     */
    void visitUnitLeader(UnitLeader unitLeader);
    /**
     * Visits advisor
     * @param advisor - structure visit
     */
    void visitAdvisor(Advisor advisor);
    /**
     * Visits sprite list
     * @param spriteList - structure visit
     */
    void visitSpriteList(SpriteList spriteList);
    /**
     * Visits localisation map
     * @param localisationMap - structure visit
     */
    void visitLocalisationMap(LocalisationMap localisationMap);

    /**
     *
     * @return base property of visitor
     */
    Property getBlock();

    /**
     * Sets property as base property in visitor
     * @param mainBlock - base property for visitor
     */
    void setBlock(Property mainBlock);
}

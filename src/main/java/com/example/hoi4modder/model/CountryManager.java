package com.example.hoi4modder.model;

import com.example.hoi4modder.game.Country;
import com.example.hoi4modder.game.GameCharacter;

import java.util.List;

public class CountryManager {
    private Lines currentStr;
    private String templateFile = """
            capital = %i
            
            set_research_slots = %i
            set_stability = %d
            set_war_support = %d
            """;
    public String countryToString(Country country) {
        return null;
    }
    public void setCharacters(List<GameCharacter> characters) {
        currentStr.removeAllContaining("recruite_character");
        for (GameCharacter gameCharacter : characters) {
            currentStr.append("recruite_character" + gameCharacter.getIdentification());
        }

    }

}

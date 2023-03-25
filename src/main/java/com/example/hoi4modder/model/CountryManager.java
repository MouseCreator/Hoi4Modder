package com.example.hoi4modder.model;

import com.example.hoi4modder.game.Country;
import com.example.hoi4modder.game.CountryPolitics;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.PopularityRadius;

import java.util.List;

public class CountryManager {
    private final Lines currentStr = new Lines();
    private final String basicTech = """
            set_technology = {
            	infantry_weapons = 1
            	infantry_weapons1 = 1
            	tech_support = 1
            	tech_engineers = 1
            	tech_recon = 1
            	motorised_infantry = 1
            	gw_artillery = 1
            	interwar_antiair = 1
            	early_fighter = 1
            	cv_early_fighter = 1
            	cv_naval_bomber1 = 1
            	naval_bomber1 = 1
            	early_bomber = 1
            	basic_train = 1
            	trench_warfare = 1
            	fleet_in_being = 1
            	fuel_silos = 1
            	gwtank_chassis = 1
            	basic_light_tank_chassis = 1
            }
            
            """;
    public void writeBlock(String name, Runnable writeBlock) {
        startBlock(name);
        writeBlock.run();
        endBlock();
    }
    public String countryToString(Country country) {
        currentStr.clear();
        currentStr.append("capital", country.getCapital());
        currentStr.appendBr("oob", country.getTag() + "1936");
        currentStr.append("set_research_slots", country.getResearchSlots());
        currentStr.append("set_stability", country.getWarSupport());
        if (country.getConvoys() > 0) {
            currentStr.append("convoys", country.getConvoys());
        }
        currentStr.append(basicTech);
        writeBlock("set_politics", ()->setPolitics(country.getPolitics()));
        writeBlock("set_popularities", ()->setPopularities(country.getPopularities()));
        setCharacters(country.getCharacters());
        endBlock();


        return currentStr.toString();
    }

    private void setPopularities(PopularityRadius popularities) {
        currentStr.append("democratic", popularities.getDemocratic());
        currentStr.append("fascism", popularities.getFascism());
        currentStr.append("communism", popularities.getCommusism());
        currentStr.append("neutrality", popularities.getNeutrality());
    }

    private void setPolitics(CountryPolitics politics) {
        currentStr.append("ruling_party", politics.getRulingParty().name());
        if (politics.isElectionsAllowed()) {
            currentStr.append("elections_allowed = yes");
            currentStr.append("elections_frequency", politics.getElectionsFrequency());
            currentStr.appendBr("last_election", politics.getLastElection());
        } else {
            currentStr.append("elections_allowed = no");
        }
    }

    private void endBlock() {
        currentStr.append("}");
    }

    private void startBlock(String blockName) {
        currentStr.append(blockName + " =  {");
    }

    public void setCharacters(List<GameCharacter> characters) {
        currentStr.removeAllContaining("recruit_character");
        for (GameCharacter gameCharacter : characters) {
            currentStr.append("recruit_character", gameCharacter.getIdentification());
        }
    }

}

package com.example.hoi4modder.service;

import com.example.hoi4modder.game.Country;

import java.io.File;

public class Destinations {
    private static final Destinations destinations = new Destinations();
    public static Destinations get() {
        return destinations;
    }
    private final String s = File.pathSeparator;
    private final String history = "history" + s;
    private final String gfx = "gfx" + s;
    private final String common = "common" + s;
    private final String txt = ".txt";
    private final String countries = "countries" + s;

    private final String localisation = "localisation" + s;

    private final String russian = "russian" + s;
    private final String yml = ".yml";

    private final String mod_countries = "mod_countries_l_russian";
    private final String mod_characters = "mod_characters_l_russian";
    private final String states = "states" + s;
    public String countryFile(Country country) {
        return history + countries + country.getTag() + " - " +
                country.getName() + txt;
    }
    public String stateFile(int id) {
        return history + states + id + txt;
    }
    public String countriesLocalisation() {
        return localisation + russian + mod_countries + yml;
    }
    public String charactersLocalisation() {
        return localisation + russian + mod_characters + yml;
    }

}

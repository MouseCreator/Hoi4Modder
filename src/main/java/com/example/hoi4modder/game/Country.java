package com.example.hoi4modder.game;

import lombok.Data;

import java.util.List;

@Data
public class Country {
    private String tag;
    private Ideology ideology;
    private List<GameCharacter> characters;

    private double stability;
    private double warSupport;
    private int researchSlots;

    private int capital;

    private int convoys;

    private PopularityRadius popularities;

    private CountryPolitics politics;
}

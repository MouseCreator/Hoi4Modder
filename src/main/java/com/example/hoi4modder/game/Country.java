package com.example.hoi4modder.game;

import lombok.Data;

@Data
public class Country {
    private String tag;
    private Ideology ideology;
    private GameCharacter characters;

    private double stability;
    private double warSupport;
    private int researchSlots;

    private int capital;

    private PopularityRadius popularities;
}

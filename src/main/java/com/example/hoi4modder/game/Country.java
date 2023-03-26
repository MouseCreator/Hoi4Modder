package com.example.hoi4modder.game;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.Data;

import java.util.List;

@Data
public class Country {
    private String tag;
    private String name;
    private Color color;
    private Image flag;
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

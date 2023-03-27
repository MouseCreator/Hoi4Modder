package com.example.hoi4modder.game;

import com.example.hoi4modder.model.files.iovisitor.SavedField;
import com.example.hoi4modder.model.files.service.Model;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Country implements Model {
    private String tag;
    private String name;
    private Color color;
    private Map<Ideology, Image> flags;
    private List<GameCharacter> characters;
    @SavedField(name = "set_stability")
    private double stability;
    @SavedField(name = "set_war_support")
    private double warSupport;
    @SavedField(name = "set_research_slots")
    private int researchSlots;

    @SavedField
    private int capital;
    @SavedField(name = "set_convoys")
    private int convoys;

    @SavedField(name = "set_popularities")
    private PopularityRadius popularities;

    @SavedField(name = "set_politics")
    private CountryPolitics politics;
}

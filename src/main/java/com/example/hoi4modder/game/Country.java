package com.example.hoi4modder.game;

import com.example.hoi4modder.model.files.iovisitor.SavedField;
import javafx.scene.image.Image;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Country {
    private String tag;
    private String name;
    private String color;
    private Map<Ideology, Image> flags;
    private List<GameCharacter> characters;
    @SavedField(name = "set_stability")
    private String stability;
    @SavedField(name = "set_war_support")
    private String warSupport;
    @SavedField(name = "set_research_slots")
    private String researchSlots;
    @SavedField
    private String capital;
    @SavedField(name = "set_convoys")
    private String convoys;

    @SavedField(name = "set_popularities")
    private PopularityRadius popularities = new PopularityRadius();

    @SavedField(name = "set_politics")
    private CountryPolitics politics = new CountryPolitics();
}

package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.LocalisationBlock;

import java.util.ArrayList;
import java.util.List;

public class PrintRules {
    private final ArrayList<String> simpleRule = new ArrayList<>(List.of(new String[] {
       "traits", "rgb"
    }));
    private final ArrayList<String> mediumRule = new ArrayList<>(List.of(new String[] {
            "provinces", "victory_points"
    }));
    public void forBlock(BlockProperty block) {
        String name = block.name();
        if (simpleRule.contains(name)) {
            block.setStyle(new SimpleStyle());
        } else if (mediumRule.contains(name)) {
            block.setStyle(new MediumStyle());
        } else {
            block.setStyle(new NormalStyle());
        }
    }

    public void forLocalisation(LocalisationBlock block) {
        block.setStyle(new LocalisationStyle());
    }
}

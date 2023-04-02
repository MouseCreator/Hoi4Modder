package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.BlockProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Class with rules that determine, which print style should the block use
 */
public class PrintRules {
    /**
     * Strings for simple style
     */
    private final ArrayList<String> simpleRule = new ArrayList<>(List.of(new String[] {
       "traits", "rgb"
    }));
    /**
     * String for medium style
     */
    private final ArrayList<String> mediumRule = new ArrayList<>(List.of(new String[] {
            "provinces", "victory_points"
    }));

    /**
     * Sets style to block
     * @param block - block to print
     */
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

}

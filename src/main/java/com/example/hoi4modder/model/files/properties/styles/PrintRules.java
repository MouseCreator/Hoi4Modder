package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.BlockProperty;
import com.example.hoi4modder.model.files.properties.LocalisationBlock;

public class PrintRules {
    public void visitBlock(BlockProperty block) {
        block.name();
    }

    public void visitLocalisation(LocalisationBlock block) {
        block.name();
    }
}

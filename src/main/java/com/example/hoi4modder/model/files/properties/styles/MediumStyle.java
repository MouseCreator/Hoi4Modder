package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.Block;
import com.example.hoi4modder.model.files.properties.Property;

public class MediumStyle implements PrintStyle{

    @Override
    public String styledList(Block block) {
        StringBuilder builder = new StringBuilder(block.prefix());
        builder.append("\n\t");
        for (Property property : block.getElements()) {
            builder.append(property).append(" ");
        }
        builder.append("\n").append(block.suffix());
        return builder.toString();
    }
}

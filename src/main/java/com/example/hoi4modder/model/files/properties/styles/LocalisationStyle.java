package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.Block;
import com.example.hoi4modder.model.files.properties.Property;

public class LocalisationStyle implements PrintStyle{
    @Override
    public String styledList(Block block) {
        StringBuilder builder = new StringBuilder(block.prefix());
        builder.append("\n");
        for (Property property : block.getElements()) {
            builder.append(" ").append(property.toFile()).append("\n");
        }
        builder.append(block.suffix());
        return builder.toString();
    }
}

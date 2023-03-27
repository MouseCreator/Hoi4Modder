package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.Block;
import com.example.hoi4modder.model.files.properties.Property;

public class SimpleStyle implements PrintStyle{

    @Override
    public String styledList(Block block) {
        StringBuilder builder = new StringBuilder(block.prefix());
        builder.append(" ");
        for (Property property : block.getElements()) {
            builder.append(property.toFile()).append(" ");
        }
        builder.append(block.suffix());
        return builder.toString();
    }
}

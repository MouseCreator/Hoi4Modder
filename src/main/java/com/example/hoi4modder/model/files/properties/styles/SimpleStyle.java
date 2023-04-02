package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.Block;
import com.example.hoi4modder.model.files.properties.Property;

/**
 * Style that prints the block in one line, separated with spaces
 */
public class SimpleStyle implements PrintStyle{
    /**
     *
     * @param block - block to print
     * @return string representation of the block in a simple style
     */
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

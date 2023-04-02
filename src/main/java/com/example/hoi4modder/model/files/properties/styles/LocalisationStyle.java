package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.Block;
import com.example.hoi4modder.model.files.properties.Property;

/**
 * Print style for localisation: puts spaces instead of tabulations in the beginning of the line
 */
public class LocalisationStyle implements PrintStyle{
    /**
     *
     * @param block - block to print
     * @return string representation of the block in localisation style
     */
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

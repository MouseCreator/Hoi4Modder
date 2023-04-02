package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.Block;
import com.example.hoi4modder.model.files.properties.Property;

/**
 * Print style that puts prefix, all values and suffix in three separate lines
 */
public class MediumStyle implements PrintStyle{
    /**
     *
     * @param block - block to print
     * @return string representation of the block in medium style
     */
    @Override
    public String styledList(Block block) {
        StringBuilder builder = new StringBuilder(block.prefix());
        builder.append("\n\t");
        for (Property property : block.getElements()) {
            builder.append(property.toFile()).append(" ");
        }
        builder.append("\n").append(block.suffix());
        return builder.toString();
    }
}

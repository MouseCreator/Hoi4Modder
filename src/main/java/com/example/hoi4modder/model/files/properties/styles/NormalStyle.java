package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.Block;
import com.example.hoi4modder.model.files.properties.Property;

/**
 * Print style that puts each child property in a new line
 */
public class NormalStyle implements PrintStyle{
    /**
     *
     * @param block - block to print
     * @return string representation of the block in a normal style
     */
    @Override
    public String styledList(Block block) {
        StringBuilder builder = new StringBuilder(block.prefix());
        builder.append("\n");
        for (Property property : block.getElements()) {
            builder.append("\t").append(property.toFile().replace("\n","\n\t")).append("\n");
        }
        builder.append(block.suffix());
        return builder.toString();
    }
}

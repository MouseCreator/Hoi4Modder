package com.example.hoi4modder.model.files.properties.styles;

import com.example.hoi4modder.model.files.properties.Block;

/**
 * Style to print block properties
 */
public interface PrintStyle {
    /**
     *
     * @param block - block to print
     * @return styled string value of the block
     */
    String styledList(Block block);
}

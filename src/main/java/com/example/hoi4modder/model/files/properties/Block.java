package com.example.hoi4modder.model.files.properties;

import com.example.hoi4modder.model.files.properties.lists.PropertyCollection;
import com.example.hoi4modder.model.files.properties.styles.PrintStyle;

/**
 * Block property is a property that may contain other properties inside
 */
public interface Block extends Property{

    /**
     *
     * @return all contained properties
     */
    PropertyCollection getElements();

    /**
     * Prefix is used by styles to write block property
     * @return string that states the beginning of the list of children (example: "={")
     */
    String prefix();

    /**
     * Suffix is used by styles to write block property
     * @return string that states the ending of the list of children (example: "}")
     */

    String suffix();

    /**
     *
     * @param style - style to print block property
     */
    void setStyle(PrintStyle style);
}

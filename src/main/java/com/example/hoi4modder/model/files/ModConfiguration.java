package com.example.hoi4modder.model.files;

import lombok.Data;

/**
 * Class that stores information about users settings that are essential for file managers
 */
@Data
public class ModConfiguration {
    /**
     * Name of modification being edited
     */
    private String modName;
    /**
     * Full path to Hoi4 game folder
     */
    private String gamePath;
    /**
     * Full path to "mods" folder in the Documents
     */
    private String modsPath;
}

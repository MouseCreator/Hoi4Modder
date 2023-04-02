package com.example.hoi4modder.game.localisation;

/**
 * Localisation type
 */
public interface Localisation {
    /**
     *
     * @return folder localisation store
     */
    String folderName();

    /**
     *
     * @return String in l_language format for localisation files
     */
    String localisationKey();
}

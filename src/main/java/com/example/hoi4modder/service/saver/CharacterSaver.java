package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.service.SavedDataContainer;

/**
 * Saver for characters and related data
 */
public class CharacterSaver {
    private final CharacterListEditor editor;
    public CharacterSaver(CharacterListEditor characterListEditor) {
        this.editor = characterListEditor;
    }

    public void save() {
        saveLoadedData();
        saveCountryCharacters();
    }
    private void saveLoadedData() {
        saveGraphicsData();
        saveLocalisationData();
    }

    private void saveCountryCharacters() {
        String tag = editor.getCountryTag();
        GameCharacterList characterList = editor.getCharacters();

        CharacterFileSaver fileSaver = new CharacterFileSaver(tag, characterList);
        fileSaver.save();
    }

    private void saveGraphicsData() {
        LoadedData data = SavedDataContainer.get().loadedData();
        DataPool<String> graphicsData = data.getGraphicsData();

        GraphicsSaver fileSaver = new GraphicsSaver(graphicsData);
        fileSaver.save();
    }

    private void saveLocalisationData() {
        LoadedData data = SavedDataContainer.get().loadedData();
        DataPool<String> localisationData = data.getLocalisationData();

        LocalisationSaver fileSaver = new LocalisationSaver(localisationData);
        fileSaver.save();
    }
}

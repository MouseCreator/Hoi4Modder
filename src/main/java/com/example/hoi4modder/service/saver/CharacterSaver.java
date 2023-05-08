package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.service.SavedDataContainer;

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
        LoadedData data = SavedDataContainer.get().loadedData();
        DataPool<String> graphicsData = data.getGraphicsData();
        DataPool<String> localisationData = data.getLocalisationData();
    }

    private void saveCountryCharacters() {
        String tag = editor.getCountryTag();
        GameCharacterList characterList = editor.getCharacters();

        CharacterFileSaver fileSaver = new CharacterFileSaver(tag, characterList);
        fileSaver.save();
    }
}

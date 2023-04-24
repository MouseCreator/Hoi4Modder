package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.model.files.manager.FileSearchService;
import com.example.hoi4modder.model.files.manager.FileSearcher;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.service.Destinations;

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
        LoadedData data = editor.getParent().getSavedData().loadedData();
        DataPool<String> graphicsData = data.getGraphicsData();
        DataPool<String> localisationData = data.getLocalisationData();
        FileSearchService searcher = editor.getParent().getSavedData().fileSearchService();
    }

    private void saveCountryCharacters() {
        String tag = editor.getCountryTag();
    }
}

package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterListEditor;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.utilities.Strings;

public class SearchingTask extends EditorListTask{

    private final String targetString;
    private final GameCharacterList initialCharacters;

    public SearchingTask(CharacterListEditor editor, String target, GameCharacterList initialCharacters) {
        this.editor = editor;
        this.targetString = target;
        this.initialCharacters = initialCharacters;
        characters = GameCharacterList.getArrayList();
    }
    @Override
    protected Void call() {
        findCharacters(targetString);
        System.out.println(characters.size());
        createListOfCharacters();
        return null;
    }

    public void findCharacters(String target) {
        if (target.startsWith("\"")) {
            loadByName(target);
        } else {
            loadByID(target);
        }
    }

    private void loadByID(String name) {
        System.out.println("Loading by ID");
        for (GameCharacter character : initialCharacters) {
            String expected = character.getIdentification();
            if (Strings.containsIgnoreCase(expected, name)) {
                characters.add(character);
            }
        }
    }

    private void loadByName(String name) {
        final String targetName = name.replace("\"", "");
        LoadedData data = editor.getParent().getSavedData().loadedData();
        DataPool<String> localisationPool = data.getLocalisationData();
        for (GameCharacter character : initialCharacters) {
            String expected = localisationPool.get(character.getName());
            if (Strings.containsIgnoreCase(expected, targetName)) {
                characters.add(character);
            }
        }
    }



}

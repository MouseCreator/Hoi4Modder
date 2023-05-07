package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.ItemContainer;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.maps.DataPool;
import com.example.hoi4modder.model.files.maps.LoadedData;
import com.example.hoi4modder.service.SavedDataContainer;
import com.example.hoi4modder.utilities.Strings;

public class SearchingTask extends EditorListTask{

    private final String targetString;
    private final GameCharacterList initialCharacters;

    public SearchingTask(ItemContainer<GameCharacter> container, String target, GameCharacterList initialCharacters) {
        super(container, initialCharacters);
        this.targetString = target;
        this.initialCharacters = initialCharacters;
        characters = GameCharacterList.getArrayList();
    }
    @Override
    protected Void call() {
        findCharacters(targetString);
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
        for (GameCharacter character : initialCharacters) {
            String expected = character.getIdentification();
            if (Strings.containsIgnoreCase(expected, name)) {
                characters.add(character);
            }
        }
    }

    private void loadByName(String name) {
        final String targetName = name.replace("\"", "");
        LoadedData data = SavedDataContainer.get().loadedData();
        DataPool<String> localisationPool = data.getLocalisationData();
        for (GameCharacter character : initialCharacters) {
            String expected = localisationPool.get(character.getName());
            if (Strings.containsIgnoreCase(expected, targetName)) {
                characters.add(character);
            }
        }
    }



}

package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.service.AbstractFactory;


public class LoadingTask extends EditorListTask {

    private final String filename;
    public LoadingTask(String filename, GameCharacterList characters) {
        this.characters = characters;
        this.filename = filename;
    }


    @Override
    protected Void call() throws Exception {
        characters.clear();
        AbstractFactory.get().getCharacterList(characters, filename);
        createListOfCharacters();
        return null;
    }
}

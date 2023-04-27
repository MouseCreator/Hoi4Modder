package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.service.AbstractFactory;
import javafx.concurrent.Task;


public class LoadingTask extends Task<Void> {

    private final GameCharacterList characters;
    private final String filename;

    public LoadingTask(String filename) {
        this.characters = GameCharacterList.getArrayList();
        this.filename = filename;
    }


    private void createListOfCharacters() {

    }

    @Override
    protected Void call() throws Exception {
        AbstractFactory.get().getCharacterList(characters, filename);
        createListOfCharacters();
        return null;
    }
}

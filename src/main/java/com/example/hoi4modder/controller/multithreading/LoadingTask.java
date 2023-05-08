package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.ItemContainer;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.service.AbstractFactory;


public class LoadingTask extends EditorListTask {

    private final String filename;
    public LoadingTask(ItemContainer<GameCharacter> container, String filename, GameCharacterList characters) {
        super(container, characters);
        this.filename = filename;
    }


    @Override
    protected Void call() throws Exception {
        characters.clear();
        AbstractFactory.get().getCharacterList(characters, filename);
        System.out.println("Characters loaded!");
        createListOfCharacters();
        return null;
    }
}

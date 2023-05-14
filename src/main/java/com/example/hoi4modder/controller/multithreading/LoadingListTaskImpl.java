package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.ItemContainer;
import com.example.hoi4modder.game.GameCharacter;
import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.service.AbstractFactory;

/**
 * Loads characters for specific country
 */
public class LoadingListTaskImpl extends EditorListTaskImpl {

    private final String filename;

    /**
     *
     * @param container - controller of the list
     * @param filename - file to be loaded
     * @param characters - list of characters to be filled
     */
    public LoadingListTaskImpl(ItemContainer<GameCharacter> container, String filename, GameCharacterList characters) {
        super(container, characters);
        this.filename = filename;
    }

    /**
     * Loads file
     * @return always null
     * @throws Exception - if task is failed
     */
    @Override
    protected Void call() throws Exception {
        characters.clear();
        AbstractFactory.get().getCharacterList(characters, filename);
        createListOfCharacters();
        return null;
    }
}

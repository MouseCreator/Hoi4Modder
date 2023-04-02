package com.example.hoi4modder.game;


import com.example.hoi4modder.game.collection.Visitable;
import com.example.hoi4modder.model.files.iovisitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * List of characters
 */
public class GameCharacterList implements Iterable<GameCharacter>, Visitable {
    private final List<GameCharacter> characterList;

    public GameCharacterList(List<GameCharacter> list) {
        this.characterList = list;
    }

    public static GameCharacterList getArrayList() {
        return new GameCharacterList(new ArrayList<>());
    }

    public void add(GameCharacter character) {
        characterList.add(character);
    }

    @Override
    public Iterator<GameCharacter> iterator() {
        return characterList.iterator();
    }

    public int size() {
        return characterList.size();
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitCharacterList(this);
    }
}

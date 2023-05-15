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

    public void clear() {
        characterList.clear();
    }

    /**
     * Creates a copy of a character and puts it in the list after its origin
     * @param indexToDuplicate - index of character to duplicate
     * @return reference to the duplicate character
     */
    public GameCharacter duplicate(int indexToDuplicate) {
        GameCharacter character = characterList.get(indexToDuplicate).cloneCharacter();
        characterList.add(indexToDuplicate, character);
        return character;
    }

    public void remove(GameCharacter gameCharacter) {
        characterList.remove(gameCharacter);
    }

    public int indexOf(GameCharacter model) {
        return characterList.indexOf(model);
    }

    public void addAfter(GameCharacter gameCharacter, GameCharacter after) {
        characterList.add(characterList.indexOf(after), gameCharacter);
    }

    public GameCharacter get(int index) {
        return characterList.get(index);
    }

    public void add(int index, GameCharacter newCharacter) {
        characterList.add(index, newCharacter);
    }
}

package com.example.hoi4modder.game;


import java.util.List;
public class GameCharacterList {
    private final List<GameCharacter> characterList;
    public GameCharacterList(List<GameCharacter> list) {
        this.characterList = list;
    }

    public void add(GameCharacter character) {
        characterList.add(character);
    }
}

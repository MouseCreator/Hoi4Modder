package com.example.hoi4modder.game;

import lombok.Data;

import java.util.List;
@Data
public class GameCharacterList {
    private List<GameCharacter> characterList;
    public GameCharacterList(List<GameCharacter> list) {
        this.characterList = list;
    }
}

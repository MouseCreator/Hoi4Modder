package com.example.hoi4modder.model.files.iovisitor;


import com.example.hoi4modder.game.GameCharacterList;

public interface Visitor {
    void visitCharacterList(GameCharacterList characterList);
}

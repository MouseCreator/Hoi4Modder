package com.example.hoi4modder.model.files.iovisitor;


import com.example.hoi4modder.game.GameCharacterList;
import com.example.hoi4modder.model.files.properties.Property;

public interface Visitor {
    void visitCharacterList(GameCharacterList characterList, Property baseProperty);
}

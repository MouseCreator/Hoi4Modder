package com.example.hoi4modder.game.roles;


import com.example.hoi4modder.model.files.iovisitor.Visitor;

import java.util.Set;

public interface CharacterRole {
    Set<String> getTraits();

    void setTraits(Set<String> traitKeys);

    void acceptVisitor(Visitor visitor);

    String getTitle();
}

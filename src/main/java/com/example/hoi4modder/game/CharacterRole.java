package com.example.hoi4modder.game;


import java.util.Set;

public interface CharacterRole {
    Set<String> getTraits();

    void setTraits(Set<String> traitKeys);
}

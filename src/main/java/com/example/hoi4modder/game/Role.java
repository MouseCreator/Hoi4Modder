package com.example.hoi4modder.game;

import lombok.Data;

import java.util.Set;
@Data
public class Role implements CharacterRole {
    protected Set<String> traits;
}

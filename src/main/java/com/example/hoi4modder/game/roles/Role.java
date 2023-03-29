package com.example.hoi4modder.game.roles;

import lombok.Data;

import java.util.Set;
@Data
public abstract class Role implements CharacterRole {
    protected Set<String> traits;
}

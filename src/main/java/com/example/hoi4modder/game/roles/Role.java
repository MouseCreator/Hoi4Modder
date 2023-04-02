package com.example.hoi4modder.game.roles;

import lombok.Data;

import java.util.Set;

/**
 * Implementation of character role
 */
@Data
public abstract class Role implements CharacterRole {
    /**
     * traits of the role
     */
    protected Set<String> traits;
}

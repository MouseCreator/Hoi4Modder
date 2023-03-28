package com.example.hoi4modder.game;

import lombok.Data;

import java.util.Set;
@Data
public class LeaderTrait implements Trait {
    private String name;
    private Set<Modifier> modifiers;
}

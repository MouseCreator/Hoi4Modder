package com.example.hoi4modder.game;

import lombok.Data;

import java.util.List;

@Data
public class GameCharacter {
    private String identification;
    private List<CharacterRole> roles;
    private List<Portrait> portraits;
}

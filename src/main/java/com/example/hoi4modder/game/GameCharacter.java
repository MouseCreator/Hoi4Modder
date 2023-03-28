package com.example.hoi4modder.game;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GameCharacter {
    private String identification;
    private String name;
    private List<CharacterRole> roles;
    private Map<String, String> portraits;
}

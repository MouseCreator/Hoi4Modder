package com.example.hoi4modder.game;

import com.example.hoi4modder.game.roles.CharacterRole;
import lombok.Data;

@Data
public class GameCharacter {
    private String identification;
    private String name;
    private FieldValueMap<CharacterRole> roles;
    private FieldValueMap<String> portraits;
}

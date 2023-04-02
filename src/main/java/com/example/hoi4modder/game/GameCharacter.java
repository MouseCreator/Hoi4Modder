package com.example.hoi4modder.game;

import com.example.hoi4modder.game.roles.CharacterRole;
import lombok.Data;

import java.util.HashMap;

@Data
public class GameCharacter {
    private String identification;
    private String name;
    private FieldValueMap<CharacterRole> roles;
    private FieldValueMap<String> portraits;

    public GameCharacter clone() throws CloneNotSupportedException {
        GameCharacter clone = (GameCharacter) super.clone();
        clone.setIdentification(this.identification + "-copy");
        clone.setName(this.name);
        clone.setRoles(new FieldValueMap<>(new HashMap<>()));
        clone.setPortraits(new FieldValueMap<>(new HashMap<>()));
        for (String role : roles.keys()) {
            clone.roles.put(role, roles.get(role));
        }
        for (String portrait : portraits.keys()) {
            clone.portraits.put(portrait, portraits.get(portrait));
        }
        return clone;
    }
    public GameCharacter(String id) {
        this.identification = id;
        this.name = id;
    }
    public GameCharacter() {
    }

}

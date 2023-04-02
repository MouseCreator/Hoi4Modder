package com.example.hoi4modder.game;

import com.example.hoi4modder.game.roles.CharacterRole;
import lombok.Data;

import java.util.HashMap;

/**
 * In-game character: country leader, advisor, army general or admiral
 */
@Data
public class GameCharacter {
    private String identification;
    private String name;
    private FieldValueMap<CharacterRole> roles;
    private FieldValueMap<String> portraits;

    /**
     * Clones character
     * @return duplicate character
     */
    public GameCharacter cloneCharacter() {
        GameCharacter clone = new GameCharacter();
        clone.setIdentification(this.identification);
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

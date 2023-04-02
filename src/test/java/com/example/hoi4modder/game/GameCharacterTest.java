package com.example.hoi4modder.game;

import com.example.hoi4modder.game.roles.Advisor;
import com.example.hoi4modder.game.roles.CharacterRole;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GameCharacterTest {

    @Test
    void testClone() throws CloneNotSupportedException {
        GameCharacter character = new GameCharacter();
        character.setName("Carl");
        character.setIdentification("AAA_carl");

        FieldValueMap<String> portraitMap = new FieldValueMap<>(new HashMap<>());
        portraitMap.put("large", "GFX_Carl_leader");
        portraitMap.put("small", "GFX_idea_carl");
        character.setPortraits(portraitMap);

        FieldValueMap<CharacterRole> rolesMap = new FieldValueMap<>(new HashMap<>());
        rolesMap.put("advisor", Advisor.createAdvisor());
        rolesMap.put("small", Advisor.createAdvisor());
        character.setRoles(rolesMap);

        GameCharacter character2 = character.cloneCharacter();
        assertEquals(character, character2);
    }
}
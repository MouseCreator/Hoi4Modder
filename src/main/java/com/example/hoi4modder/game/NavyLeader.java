package com.example.hoi4modder.game;

import lombok.Data;

import java.util.Set;

@Data
public class NavyLeader {
    private int skill;
    private int attackSkill;
    private int defenceSkill;
    private int maneuveringSkill;
    private int coordinationSkill;
    private Set<String> traits;
}

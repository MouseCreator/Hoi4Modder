package com.example.hoi4modder.game;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class NavyLeader extends Role {
    private int skill;
    private int attackSkill;
    private int defenceSkill;
    private int maneuveringSkill;
    private int coordinationSkill;
    private Set<String> traits;
}

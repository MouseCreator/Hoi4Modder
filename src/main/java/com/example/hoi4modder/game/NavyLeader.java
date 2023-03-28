package com.example.hoi4modder.game;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class NavyLeader extends Role {
    private int skill;
    private int attackSkill;
    private int defenceSkill;
    private int maneuveringSkill;
    private int coordinationSkill;

    public static NavyLeader getNavyLeader() {
        NavyLeader leader = new NavyLeader();
        leader.setSkill(1);
        leader.setManeuveringSkill(1);
        leader.setCoordinationSkill(1);
        leader.setDefenceSkill(1);
        leader.setAttackSkill(1);
        leader.setTraits(new HashSet<>());
        return leader;
    }
}

package com.example.hoi4modder.game.roles;

import com.example.hoi4modder.model.files.iovisitor.Visitor;
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

    public static NavyLeader createCountryLeader() {
        NavyLeader leader = new NavyLeader();
        leader.setSkill(1);
        leader.setManeuveringSkill(1);
        leader.setCoordinationSkill(1);
        leader.setDefenceSkill(1);
        leader.setAttackSkill(1);
        leader.setTraits(new HashSet<>());
        return leader;
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitNavyLeader(this);
    }

    @Override
    public String getTitle() {
        return "navy_leader";
    }
}

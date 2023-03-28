package com.example.hoi4modder.game;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitLeader extends Role{
    private int skill;
    private boolean isFieldMarshal;
    private int attackSkill;
    private int defenceSkill;
    private int planningSkill;
    private int logisticsSkill;

    public static UnitLeader getCorpsCommander() {
        UnitLeader commander = weakUnitLeader();
        commander.isFieldMarshal=false;
        return commander;
    }
    public static UnitLeader getFieldMarshal() {
        UnitLeader commander = weakUnitLeader();
        commander.isFieldMarshal=true;
        return commander;
    }
    private static UnitLeader weakUnitLeader() {
        UnitLeader commander = new UnitLeader();
        commander.setSkill(1);
        commander.setAttackSkill(1);
        commander.setDefenceSkill(1);
        commander.setPlanningSkill(1);
        commander.setLogisticsSkill(1);
        return commander;
    }
}

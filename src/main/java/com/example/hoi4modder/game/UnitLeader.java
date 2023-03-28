package com.example.hoi4modder.game;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnitLeader extends Role{
    private int skill;
    private boolean fieldMarshal;
    private int attackSkill;
    private int defenceSkill;
    private int planningSkill;
    private int logisticsSkill;
}

package com.example.hoi4modder.game;

import lombok.Data;

import java.util.List;
@Data
public class CorpsCommander implements CharacterRole{
    private int attackSkill;
    private int defenceSkill;
    private int planningSkill;
    private int logisticsSkill;
    private List<ArmyTrait> traits;
}

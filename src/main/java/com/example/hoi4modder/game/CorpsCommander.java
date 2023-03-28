package com.example.hoi4modder.game;

import lombok.Data;

import java.util.Set;

@Data
public class CorpsCommander implements CharacterRole{
    private int skill;
    private int attackSkill;
    private int defenceSkill;
    private int planningSkill;
    private int logisticsSkill;
    private Set<String> traits;
}

package com.example.hoi4modder.game;

import lombok.Data;

import java.util.Set;

@Data
public class Advisor implements CharacterRole{
    private String slot;
    private String token;
    private String ledger;
    private Set<LeaderTrait> traits;
}

package com.example.hoi4modder.game;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class Advisor extends Role{
    private String slot;
    private String token;
    private String ledger;
    private int cost;

    public Advisor() {
    }

    public static Advisor createAdvisor() {
        Advisor result = new Advisor();
        result.cost = 150;
        result.slot = "political_advisor";
        result.ledger = "";
        result.token = "NO_TOKEN";
        result.setTraits(new HashSet<>());
        return result;
    }
}

package com.example.hoi4modder.game;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class CountryLeader extends Role {
    private String ideology;

    public static CountryLeader getCountryLeader() {
        CountryLeader leader = new CountryLeader();
        leader.setTraits(new HashSet<>());
        leader.setIdeology("socialism");
        return leader;
    }
}

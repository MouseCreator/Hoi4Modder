package com.example.hoi4modder.game;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CountryLeader extends Role {
    private String ideology;
}

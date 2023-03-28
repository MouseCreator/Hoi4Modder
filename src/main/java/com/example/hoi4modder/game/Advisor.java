package com.example.hoi4modder.game;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Advisor extends Role{
    private String slot;
    private String token;
    private String ledger;
}

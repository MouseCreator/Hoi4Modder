package com.example.hoi4modder.game;

import lombok.Data;

@Data
public class CountryPolitics {
    private Ideology rulingParty;
    private boolean electionsAllowed;
    private int electionsFrequency;
    private String lastElection;
}

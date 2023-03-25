package com.example.hoi4modder.game;

import lombok.Data;

import java.util.List;

@Data
public class State {
    private Country owner;
    private List<String> claims;
    private List<String> core;

}

package com.example.hoi4modder.game.buildings;

public class Infrastructure extends Building {
    @Override
    public String getName() {
        return "infrastructure";
    }

    public int getMaxLevel() {
        return 5;
    }
}

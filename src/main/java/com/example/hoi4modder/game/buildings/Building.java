package com.example.hoi4modder.game.buildings;


public abstract class Building {
    private int level;

    public abstract String getName();
    public boolean getIsCoastal() {
        return false;
    }
    public int getLevel() {
        return level;
    }

}

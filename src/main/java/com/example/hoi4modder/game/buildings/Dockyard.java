package com.example.hoi4modder.game.buildings;

public class Dockyard extends Building{
    @Override
    public String getName() {
        return "dockyard";
    }
    @Override
    public boolean getIsCoastal() {
        return true;
    }
}

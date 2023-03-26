package com.example.hoi4modder.game.buildings;

public class CoastalBunker extends ProvinceBuilding {
    @Override
    public String getName() {
        return "coastal_bunker";
    }
    @Override
    public boolean getIsCoastal() {
        return true;
    }
}

package com.example.hoi4modder.game.buildings;

public abstract class ProvinceBuilding extends LimitedBuilding {
    private int province;

    @Override
    public int getMaxLevel() {
        return 10;
    }
    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }
}

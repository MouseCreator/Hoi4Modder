package com.example.hoi4modder.game.common;

public class DynamicCountry implements Country {

    private String tag;
    public DynamicCountry() {
        this.tag = "";
    }
    public DynamicCountry(String tag) {
        this.tag = tag;
    }

    @Override
    public String getTag() {
        return tag;
    }

    public void setTag(String countryTag) {
        this.tag = countryTag;
    }
}

package com.example.hoi4modder.game.common;

public class CountryImpl implements Country {

    private String tag;
    public CountryImpl() {
        this.tag = "";
    }
    public CountryImpl(String tag) {
        this.tag = tag;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(String countryTag) {
        this.tag = countryTag;
    }
}

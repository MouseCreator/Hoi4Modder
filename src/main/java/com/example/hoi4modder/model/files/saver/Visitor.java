package com.example.hoi4modder.model.files.saver;

import com.example.hoi4modder.game.Country;

public interface Visitor {
    void visitCountry(Country country);
}

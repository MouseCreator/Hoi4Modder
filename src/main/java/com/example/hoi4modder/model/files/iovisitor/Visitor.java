package com.example.hoi4modder.model.files.iovisitor;

import com.example.hoi4modder.game.Country;

public interface Visitor {
    void visitCountry(Country country);
}

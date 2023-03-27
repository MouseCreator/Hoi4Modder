package com.example.hoi4modder.model.files.iovisitor;

import com.example.hoi4modder.game.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {
    @Test
    void someTest() {
        Country c = new Country();
        iReceiveCountry(c);
    }

    void iReceiveCountry(Country country) {
    }
}
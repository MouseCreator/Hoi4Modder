package com.example.hoi4modder.model.files.saver;

import com.example.hoi4modder.game.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoaderTest {
    @Test
    void someTest() {
        Country c = new Country();
        iReceiveCountry(c);
        assertEquals( 3, c.getCapital() );
        assertEquals( 100, c.getConvoys() );
    }

    void iReceiveCountry(Country country) {
        country.setCapital(3);
        country.setConvoys(100);
    }
}
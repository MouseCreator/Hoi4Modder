package com.example.hoi4modder.model.files.factories;

import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.SavedElement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyFactoryImplTest {

    @Test
    void toProperty() {
        String[] strings = {
                "porsche:0 \"«Порше»\"",
                "ernst_udet:0 \"Эрнст Удет\"",
                "set_popularities = {",
                "isolation",
                "democratic = 7",
                "recruit_character = ENT_leyla_athella",
                "basic_heavy_battery = 1",
                "9357 1"
        };
        PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();
        for (String s : strings) {
            SavedElement result = propertyFactory.toProperty(s);
            String propertyString = result.toFile();
            assertEquals(s, propertyString);
        }
    }
}
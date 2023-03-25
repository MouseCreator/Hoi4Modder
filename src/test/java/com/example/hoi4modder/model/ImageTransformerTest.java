package com.example.hoi4modder.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTransformerTest {
    @Test
    void toPortrait() {
        try {
            ImageTransformer transformer = new ImageTransformer();
            transformer.toPortrait();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
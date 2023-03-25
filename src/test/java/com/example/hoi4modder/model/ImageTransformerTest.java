package com.example.hoi4modder.model;

import com.example.hoi4modder.service.ImageTransformer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTransformerTest {
    @Test
    void toPortrait() {
        try {
            ImageTransformer transformer = new ImageTransformer();
            transformer.portrait();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
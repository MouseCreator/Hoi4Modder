package com.example.hoi4modder.model.files.images;

import com.example.hoi4modder.service.Destinations;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
class DirectSurfaceManagerTest {
    @Test
    void testDDSLoader() throws IOException {
        DirectSurfaceManager ddsFiles = new DirectSurfaceManager();
        Image img = ddsFiles.loadDDS(Destinations.get().testPortrait());
        assertNotNull(img);
    }
}
package com.example.hoi4modder.model.files.images;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class DirectSurfaceManagerTest {
    @Test
    void testDDSLoader() throws IOException {
        DirectSurfaceManager ddsFiles = new DirectSurfaceManager();
        ddsFiles.loadDDS("C:\\Users\\mysha\\Documents\\Paradox Interactive\\Hearts of Iron IV\\mod\\leylamod12c\\gfx\\leaders\\MNK\\MNK_leader_paulis.dds");
    }
}
package com.example.hoi4modder.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlagsManager {
    private void saveFlag(BufferedImage image) throws IOException {
        File outputfile = new File("image.jpg");
        ImageIO.write(image, "jpg", outputfile);
    }
}

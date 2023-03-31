package com.example.hoi4modder.model.files.images;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class DirectSurfaceManager {

    public java.awt.image.BufferedImage awtImage(String filename) throws IOException {
        FileInputStream inputStream = new FileInputStream(filename);
        byte [] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        int [] pixels = DDSReader.read(buffer, DDSReader.ARGB, 0);
        int width = DDSReader.getWidth(buffer);
        int height = DDSReader.getHeight(buffer);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        return image;
    }
}

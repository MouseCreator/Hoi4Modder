package com.example.hoi4modder.model.files.images;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class for loading DDS images as javafx images (for preview)
 */
public class DirectSurfaceManager {
    public Image loadDDS(String filename) throws IOException {
        return toFxImage(loadAwtImage(filename));
    }
    private java.awt.image.BufferedImage loadAwtImage(String filename) throws IOException {
        FileInputStream inputStream = new FileInputStream(filename);
        byte [] buffer = new byte[inputStream.available()];
        int b =inputStream.read(buffer);
        assert b == inputStream.available();
        inputStream.close();
        int [] pixels = DDSReader.read(buffer, DDSReader.ARGB, 0);
        int width = DDSReader.getWidth(buffer);
        int height = DDSReader.getHeight(buffer);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        return image;
    }
    private Image toFxImage(BufferedImage awtImage) {
        return javafx.embed.swing.SwingFXUtils.toFXImage(awtImage, null);
    }

}

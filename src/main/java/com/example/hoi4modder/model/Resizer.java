package com.example.hoi4modder.model;
import java.awt.*;
import java.awt.image.BufferedImage;
public class Resizer {
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
    public BufferedImage resizeToSmall(BufferedImage origin) {
        return resizeImage(origin, 10, 7);
    }
    public BufferedImage resizeToMedium(BufferedImage origin) {
        return resizeImage(origin, 41, 26);
    }
    public BufferedImage resizeToBig(BufferedImage origin) {
        return resizeImage(origin, 82, 52);
    }
}

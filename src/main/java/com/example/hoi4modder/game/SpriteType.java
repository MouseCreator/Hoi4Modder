package com.example.hoi4modder.game;

import lombok.Data;

/**
 * Sprite: graphic object
 */
@Data
public class SpriteType  {
    private String name;
    private String textureFile;
    public SpriteType() {
    }
    public SpriteType(String name, String textureFile) {
        this.name = name;
        this.textureFile = textureFile;
    }
}

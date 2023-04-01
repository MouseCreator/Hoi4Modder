package com.example.hoi4modder.game.collection;

import com.example.hoi4modder.game.SpriteType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpriteList implements Iterable<SpriteType>{

    private final ArrayList<SpriteType> sprites = new ArrayList<>();
    public void add(SpriteType sprite) {
        sprites.add(sprite);
    }
    public void remove(SpriteType sprite) {
        sprites.remove(sprite);
    }

    @Override
    public Iterator<SpriteType> iterator() {
        return sprites.iterator();
    }
}

package com.example.hoi4modder.game.collection;

import com.example.hoi4modder.game.SpriteType;
import com.example.hoi4modder.model.files.iovisitor.Visitor;

import java.util.ArrayList;
import java.util.Iterator;

public class SpriteList implements Iterable<SpriteType>, Visitable{

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

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitSpriteList(this);
    }
}

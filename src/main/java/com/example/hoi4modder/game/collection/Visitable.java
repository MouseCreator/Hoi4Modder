package com.example.hoi4modder.game.collection;

import com.example.hoi4modder.model.files.iovisitor.Visitor;

public interface Visitable {
    void acceptVisitor(Visitor visitor);
}

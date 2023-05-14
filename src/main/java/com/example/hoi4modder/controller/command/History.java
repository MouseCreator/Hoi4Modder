package com.example.hoi4modder.controller.command;

public interface History {
    void undo();
    void redo();

    void add(Command command);
}

package com.example.hoi4modder.controller.command;


public abstract class CharacterItemCommand implements Command{

    protected final ControlCallable controlCallable;

    public CharacterItemCommand(ControlCallable controlCallable) {
        this.controlCallable = controlCallable.call().callSelf();
    }
}

package com.example.hoi4modder.controller.command;


import java.util.concurrent.Callable;

public abstract class CharacterItemCommand implements Command{

    private final Callable<ControlConnectable> pathToConnectedItem;

    public CharacterItemCommand(Callable<ControlConnectable> pathToConnectedItem) {
        this.pathToConnectedItem = pathToConnectedItem;
    }
    protected ControlConnectable getItemController() {
        try {
            return pathToConnectedItem.call();
        } catch (Exception e) {
             e.printStackTrace();
             throw new RuntimeException("Cannot access control connectable with callable method");
        }
    }
}

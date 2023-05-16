package com.example.hoi4modder.controller.command;

/**
 * Command for character item
 */
public abstract class CharacterItemCommand implements Command{

    protected final ControlConnectableCallable controlConnectableCallable;

    /**
     *
     * @param controlConnectableCallable - callable function, which returns instance from which callSelf() may be extracted
     */
    public CharacterItemCommand(ControlConnectableCallable controlConnectableCallable) {
        this.controlConnectableCallable = controlConnectableCallable.call().callSelf();
    }
}

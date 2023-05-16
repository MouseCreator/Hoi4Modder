package com.example.hoi4modder.controller.command;

import java.util.concurrent.Callable;

/**
 * Callable function that returns connectable item
 */
public abstract class ControlConnectableCallable implements Callable<ControlConnectable> {
    public abstract ControlConnectable call();
}

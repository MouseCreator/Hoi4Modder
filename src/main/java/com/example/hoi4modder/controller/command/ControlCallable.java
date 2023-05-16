package com.example.hoi4modder.controller.command;

import java.util.concurrent.Callable;

public abstract class ControlCallable implements Callable<ControlConnectable> {
    public abstract ControlConnectable call();
}

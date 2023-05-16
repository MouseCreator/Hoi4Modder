package com.example.hoi4modder.controller.command;

public interface ControlConnectable {
    ControlConnector getConnector();

    ControlConnectableCallable callSelf();
}

package com.example.hoi4modder.controller.character_extra;

public class AutoProcess {

    private boolean isAutoProcess;

    public void start() {
        isAutoProcess = true;
    }
    public void end() {
        isAutoProcess = false;
    }

    public boolean isAutoProcess() {
        return isAutoProcess;
    }
}

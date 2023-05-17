package com.example.hoi4modder.controller.command.roles;

public class ControlStatus {
    private boolean isListenerEnabled;

    public ControlStatus() {
        this.isListenerEnabled = true;
    }

    public boolean isListenerEnabled() {
        return isListenerEnabled;
    }

    public void setListenerEnabled(boolean listenerEnabled) {
        isListenerEnabled = listenerEnabled;
    }
}

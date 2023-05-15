package com.example.hoi4modder.controller.command;

/**
 * Auxiliary class that stores last state of class, used in memory
 */
class BindMemory<T> {
    private T memoryValue;

    public BindMemory() {
        memoryValue = null;
    }

    public BindMemory(T initial) {
        this.memoryValue = initial;
    }

    public void setMemory(T newValue) {
        this.memoryValue = newValue;
    }

    public T getMemory() {
        return memoryValue;
    }
}
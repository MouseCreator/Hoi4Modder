package com.example.hoi4modder.controller.command;

/**
 * Auxiliary class that stores last state of class, used in memory
 */
public class BindMemory<T> {
    private T memoryValue;

    public BindMemory() {
        memoryValue = null;
    }

    public BindMemory(T initial) {
        this.memoryValue = initial;
    }

    /**
     * Sets new value to memory
     * @param newValue - value to be stored in memory
     */
    public void setMemory(T newValue) {
        this.memoryValue = newValue;
    }

    /**
     *
     * @return value, stored in memory
     */
    public T getMemory() {
        return memoryValue;
    }
}
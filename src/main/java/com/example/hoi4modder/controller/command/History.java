package com.example.hoi4modder.controller.command;

public interface History {
    /**
     * Cancel last action
     */
    void undo();

    /**
     * Restore last canceled action
     */
    void redo();

    /**
     * Push new command to list of commands that can be undone
     * @param command - command to add
     */
    void add(Command command);
}

package com.example.hoi4modder.controller.command;

/**
 * History with console logs;
 * Prints itself in console on the BEGINNING of any action
 * Works as a Decorator
 */
public class LoggableHistory implements History{
    private final History history;
    public LoggableHistory(History decoratedHistory) {
        this.history = decoratedHistory;
    }
    private void print() {
        System.out.println(history);
    }
    @Override
    public void undo() {
        print();
        history.undo();
    }

    @Override
    public void redo() {
        print();
        history.redo();
    }

    @Override
    public void add(Command command) {
        print();
        history.add(command);
    }

    @Override
    public boolean isRedo() {
        return history.isRedo();
    }

    @Override
    public boolean isUndo() {
        return history.isUndo();
    }
}

package com.example.hoi4modder.controller.command;

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
}

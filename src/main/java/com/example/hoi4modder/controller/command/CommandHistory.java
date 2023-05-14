package com.example.hoi4modder.controller.command;

import java.util.Stack;

public class CommandHistory {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    public void add(Command command) {
        undoStack.push(command);
    }
    public void undo() {
        if (undoStack.empty()) {
            return;
        }
        Command lastCommand = undoStack.pop();
        lastCommand.execute();
        redoStack.push(lastCommand);
    }
    public void redo() {
        if (redoStack.isEmpty()) {
            return;
        }
        Command lastCommand = redoStack.pop();
        lastCommand.execute();
        undoStack.push(lastCommand);
    }
}

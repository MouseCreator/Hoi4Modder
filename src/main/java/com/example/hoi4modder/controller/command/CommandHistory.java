package com.example.hoi4modder.controller.command;

import java.util.Stack;

/**
 * History implementation that uses stacks for undo/redo commands;
 * Does not have a limit for number of commands
 */
public class CommandHistory {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    /**
     * Adds new command
     * Clears redo stack
     * @param command - command to add
     */
    public void add(Command command) {
        undoStack.push(command);
        redoStack.clear();
    }

    /**
     * Pops last command from undo stack
     */
    public void undo() {
        if (undoStack.isEmpty()) {
            return;
        }
        Command lastCommand = undoStack.pop();
        lastCommand.undo();
        redoStack.push(lastCommand);
    }

    /**
     * Pops last command from redo stack
     */
    public void redo() {
        if (redoStack.isEmpty()) {
            return;
        }
        Command lastCommand = redoStack.pop();
        lastCommand.execute();
        undoStack.push(lastCommand);
    }

    /**
     *
     * @return string representation of both redo and undo stacks
     */
    @Override
    public String toString() {
        return "FixedSizeCommandHistory{" +
                "undoStack=" + undoStack +
                ", redoStack=" + redoStack +
                '}';
    }
}

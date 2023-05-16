package com.example.hoi4modder.controller.command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class FixedSizeCommandHistory implements History{
    private final Deque<Command> undoStack;
    private final Stack<Command> redoStack;
    private final int size;
    public FixedSizeCommandHistory(int size) {
        this.undoStack = new ArrayDeque<>(size);
        redoStack = new Stack<>();
        this.size = size;
    }

    /**
     * Adds new action to undo list;
     * If the list is full, first added action will be removed
     * @param command - command to add
     */
    public void add(Command command) {
        if (undoStack.size() == size) {
            undoStack.pollLast();
        }
        undoStack.addFirst(command);
        redoStack.clear();
    }

    /**
     * Cancels last action
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
     * Cancels last undo
     */
    public void redo() {
        if (redoStack.isEmpty()) {
            return;
        }
        Command lastCommand = redoStack.pop();
        lastCommand.execute();
        undoStack.push(lastCommand);
    }

    @Override
    public String toString() {
        return "FixedSizeCommandHistory{" +
                "undoStack=" + undoStack +
                ", redoStack=" + redoStack +
                ", size=" + size +
                '}';
    }
}

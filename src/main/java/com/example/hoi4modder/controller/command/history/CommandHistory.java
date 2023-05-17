package com.example.hoi4modder.controller.command.history;

import com.example.hoi4modder.controller.command.Command;

import java.util.Stack;

/**
 * History implementation that uses stacks for undo/redo commands;
 * Does not have a limit for number of commands
 */
public class CommandHistory implements History{
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
    private boolean redoInProcess = false;
    private boolean undoInProcess = false;
    @Override
    public boolean isRedo() {
        return redoInProcess;
    }

    @Override
    public boolean isUndo() {
        return undoInProcess;
    }

    /**
     * Pops last command from undo stack
     */
    public void undo() {
        if (undoStack.isEmpty()) {
            return;
        }
        Command lastCommand = undoStack.pop();
        undoInProcess = true;
        lastCommand.undo();
        undoInProcess = false;
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
        redoInProcess = true;
        lastCommand.execute();
        redoInProcess = false;
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
    private boolean isAuto;
    public boolean isAuto() {
        return isAuto;
    }

    public void startAuto() {
        this.isAuto = true;
    }
    public void endAuto() {
        this.isAuto = false;
    }
}

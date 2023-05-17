package com.example.hoi4modder.controller.command.roles;

public class IgnoredActionRunner {
    public void runAction(UndoRedoManager undoRedoManager, Runnable runnable) {
        if (undoRedoManager == null) {
            runnable.run();
            return;
        }
        undoRedoManager.saveState();
        undoRedoManager.disableAll();
        runnable.run();
        undoRedoManager.lastState();
    }
}

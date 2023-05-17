package com.example.hoi4modder.controller.command.roles;


import java.util.concurrent.Callable;

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
    public <V> V callAction(UndoRedoManager undoRedoManager, Callable<V> callable) throws Exception {
        if (undoRedoManager == null) {
            return callable.call();
        }
        undoRedoManager.saveState();
        undoRedoManager.disableAll();
        V result = callable.call();
        undoRedoManager.lastState();
        return result;
    }

}

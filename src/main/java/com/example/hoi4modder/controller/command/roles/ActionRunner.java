package com.example.hoi4modder.controller.command.roles;

public class ActionRunner {
    private static IgnoredActionRunner runner = new IgnoredActionRunner();
    public static IgnoredActionRunner get() {
        if (runner == null)
            runner = new IgnoredActionRunner();
        return runner;
    }
}

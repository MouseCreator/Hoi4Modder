package com.example.hoi4modder.controller.multithreading;

import com.example.hoi4modder.controller.CharacterListEditor;
import javafx.concurrent.Task;

public class SearchingTask extends Task<Void> {

    private final CharacterListEditor editor;

    public SearchingTask(CharacterListEditor editor) {
        this.editor = editor;
    }


    @Override
    protected Void call() {
        editor.findCharacters();
        return null;
    }
}

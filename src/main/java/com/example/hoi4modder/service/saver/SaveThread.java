package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.controller.CharacterListEditor;

public class SaveThread extends Thread{

    private final CharacterSaver saver;

    public SaveThread (CharacterListEditor editor) {
        saver = new CharacterSaver(editor);
    }
    @Override
    public void run() {
        saver.save();
    }
}

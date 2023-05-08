package com.example.hoi4modder.service.saver;

import com.example.hoi4modder.controller.CharacterListEditor;

/**
 * Thread to save character lists on the background state.
 * Currently used for character editor
 */
public class SaveThread extends Thread{

    private final CharacterSaver saver;

    public SaveThread (CharacterListEditor editor) {
        saver = new CharacterSaver(editor);
    }

    /**
     * Starts saving progress
     */
    @Override
    public void run() {
        saver.save();
    }
}

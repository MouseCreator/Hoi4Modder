package com.example.hoi4modder.model.files.properties.printstyles;

import com.example.hoi4modder.model.files.properties.SavedElement;

public class LazyPrintStyle implements PrintStyle{
    @Override
    public String printStyled(ListElement element) {
        StringBuilder builder = new StringBuilder();
        for(;;) {

        }
    }

    @Override
    public String printStyled(SavedElement element, int depth) {
        return null;
    }
}

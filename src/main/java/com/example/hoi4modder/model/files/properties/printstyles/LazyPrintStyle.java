package com.example.hoi4modder.model.files.properties.printstyles;

import com.example.hoi4modder.model.files.properties.SavedElement;

public class LazyPrintStyle implements PrintStyle{
    @Override
    public String printStyled(ListElement list) {
        StringBuilder builder = new StringBuilder(list.prefix());
        for(SavedElement savedElement : list.getElements()) {
            builder.append(savedElement.toFile());
        }
        builder.append(list.suffix());
        return builder.toString();
    }

    @Override
    public String printStyled(SavedElement element, int depth) {
        return null;
    }
}

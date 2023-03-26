package com.example.hoi4modder.model.files.properties.printstyles;

import com.example.hoi4modder.model.files.properties.ListElement;
import com.example.hoi4modder.model.files.properties.SavedElement;

public class LazyPrintStyle implements PrintStyle{
    @Override
    public String printStyled(ListElement list) {
        StringBuilder builder = new StringBuilder(list.prefix());
        builder.append(" ");
        for(SavedElement savedElement : list.getElements()) {
            builder.append(savedElement.toFile()).append(" ");
        }
        builder.append(list.suffix());
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public boolean matches(String styledString) {
        return styledString.contains("{") && styledString.contains("}");
    }

}

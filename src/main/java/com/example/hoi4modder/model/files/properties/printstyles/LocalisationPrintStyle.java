package com.example.hoi4modder.model.files.properties.printstyles;

import com.example.hoi4modder.model.files.properties.ListElement;
import com.example.hoi4modder.model.files.properties.SavedElement;

public class LocalisationPrintStyle implements PrintStyle{
    @Override
    public String printStyled(ListElement list) {
        StringBuilder builder = new StringBuilder();
        builder.append(list.prefix());
        for (SavedElement element : list.getElements()) {
            builder.append(" ").append(element.toFile()).append("\n");
        }
        builder.append(list.suffix());
        return builder.toString();
    }

    @Override
    public boolean matches(String styledString) {
        return styledString.startsWith(" ");
    }

}

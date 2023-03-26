package com.example.hoi4modder.model.files.properties.printstyles;

import com.example.hoi4modder.model.files.properties.ListElement;
import com.example.hoi4modder.model.files.properties.SavedElement;

public class NormalPrintStyle implements PrintStyle{
    @Override
    public String printStyled(ListElement list) {
        StringBuilder builder = new StringBuilder(list.prefix());
        builder.append("\n");
        for (SavedElement element : list.getElements()) {
            builder.append(element.toFile()).append("\n");
        }
        builder.append(list.prefix());
        String result = builder.toString();
        return result.replace("\n", "\n\t");

    }

}

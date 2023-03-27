package com.example.hoi4modder.model.files;

import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.factories.PropertyFactoryImpl;

import java.util.List;
public class StringToSavedElementConvertor {
    PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();
    public Property forStructuredFile(List<String> input) {
        StringBuilder builder = new StringBuilder();
        for (String line : input) {
            line = toNormalizedLine(line);
            if (line.isEmpty())
                continue;
            line = line.replace(" =", "=");
            line = line.replace("= ", "=");
            line = line.replace(":0 ", ":0");
            line = line.trim().replaceAll(" +", " ");
            builder.append(line).append(" ");
        }
        String fullExpression = builder.toString();
        //String[] expressions = fullExpression.split(" ");
        return propertyFactory.toProperty(fullExpression);
    }

    private String toNormalizedLine(String line) {
        String result = line.replace("\t", "");
        if (result.contains("#"))
            result = result.split("#", 2)[0];
        return result.trim();
    }


}

package com.example.hoi4modder.model.files;

import com.example.hoi4modder.model.files.properties.Property;
import com.example.hoi4modder.model.files.properties.factories.PropertyFactoryImpl;

import java.util.ArrayList;
import java.util.List;
public class StringToPropertyConvertor {
    private final PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();
    public Property forStructuredFile(List<String> input) {
        String fullExpression = normalizeString(input);
        return propertyFactory.toProperty(fullExpression);
    }

    protected String normalizeString(List<String> input) {
        StringBuilder builder = new StringBuilder();
        for (String line : input) {
            line = toNormalizedLine(line);
            if (line.isEmpty())
                continue;
            line = line.replace(" =", "=");
            line = line.replace("= ", "=");
            line = line.replace("{", "{ ");
            line = line.replace("}", " } ");
            if (line.matches("[a-zA-Z0-9-_]+:[0-9]+ \"(.+)\"")) {
                String[] dotSplit = line.split("\"", 2);
                line = dotSplit[0].trim() + "\"" + dotSplit[1];
            }
            line = line.trim().replaceAll(" +", " ");
            if (line.isEmpty())
                continue;
            builder.append(line).append(" ");
        }
        return builder.toString();
    }

    public Property forStructuredFile(String input) {
       return forStructuredFile(toLines(input));
    }
    private String toNormalizedLine(String line) {
        String result = line.replace("\t", "");
        if (result.contains("#"))
            result = result.split("#", 2)[0];
        return result.trim();
    }


    public ArrayList<String> toLines(String file) {
        return new ArrayList<>(List.of(file.split("\n")));
    }
}

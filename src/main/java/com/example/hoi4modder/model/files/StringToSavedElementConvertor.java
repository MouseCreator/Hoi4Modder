package com.example.hoi4modder.model.files;

import com.example.hoi4modder.model.files.properties.factories.PropertyFactoryImpl;
import com.example.hoi4modder.model.files.properties.*;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class StringToSavedElementConvertor {
    PropertyFactoryImpl propertyFactory = new PropertyFactoryImpl();
    public SavedList forStructuredFile(List<String> input) {
        SavedElementList result = new SavedElementList();
        StringBuilder builder = new StringBuilder();
        for (String line : input) {
            line = toNormalizedLine(line);
            if (line.isEmpty())
                continue;
            line = line.replace(" =", "=");
            line = line.replace("= ", "=");
            line = line.trim().replaceAll(" +", " ");
            builder.append(line).append(" ");
        }
        String fullExpression = builder.toString();
        String[] expressions = fullExpression.split(" ");
        Queue<String> strings = new LinkedBlockingQueue<>(List.of(expressions));
        return new SavedElementList(fromExpressions(strings));
    }
    private SavedListArray fromExpressions(Queue<String> expressions) {
        SavedListArray arr = new SavedListArray();
        while (!expressions.isEmpty()) {
            String current = expressions.poll();
            if (current.endsWith("={")) {
                arr.add(new BlockListProperty(current.substring(0, current.length()-2), fromExpressions(expressions)));
            } else if (current.startsWith("}")) {
                return arr;
            } else {
                arr.add(propertyFactory.toProperty(current));
            }
        }
        return arr;
    }

    private String toNormalizedLine(String line) {
        String result = line.replace("\t", "");
        if (result.contains("#"))
            result = result.split("#", 2)[0];
        return result.trim();
    }


}

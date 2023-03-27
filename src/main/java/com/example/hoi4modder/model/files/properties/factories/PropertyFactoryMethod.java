package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;

abstract class PropertyFactoryMethod implements ChainedFactory{

    public abstract ChainedFactory next();

    @Override
    public Property createProperty(String origin) {
        if (canHandle(origin)) {
            return toProperty(origin);
        } else {
            return next().createProperty(origin);
        }
    }


    @Override
    public boolean canHandle(String origin) {
        return getProperty().containsDelimiter(origin.split(" ")[0]);
    }

    public abstract Property toProperty(String origin);

    protected abstract Property getProperty();

    protected String[] split(String string) {
        String[] strings = string.split(" ", 2);
        if (hasNotClosedBracket(strings[0])) {
            String[] toBrackets = strings[1].split("\"", 2);
            strings[0] += toBrackets[2];
            strings[0] += "\"";
            strings[1] = toBrackets[1];
        }
        return strings;
    }
    private boolean hasNotClosedBracket(String str) {
        return countBracketOccurrence(str) % 2 == 1;
    }
    private int countBracketOccurrence(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '"')
                count++;
        }
        return count;
    }
}



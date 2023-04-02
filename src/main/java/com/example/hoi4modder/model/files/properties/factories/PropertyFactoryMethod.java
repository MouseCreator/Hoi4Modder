package com.example.hoi4modder.model.files.properties.factories;

import com.example.hoi4modder.model.files.properties.Property;

/**
 * Produces properties.
 * If it cannot handle string, delegates to the next factory in chain
 */
abstract class PropertyFactoryMethod implements ChainedFactory{
    /**
     *
     * @return next factory in chain
     */
    public abstract ChainedFactory next();

    /**
     *
     * @param origin - string to parse
     * @return property created on this or one of the next factories
     */
    @Override
    public Property createProperty(String origin) {
        if (canHandle(origin)) {
            return toProperty(origin);
        } else {
            return next().createProperty(origin);
        }
    }

    /**
     *
     * @param origin - string representation of property
     * @return true, if factory is able to parse string to property
     */
    @Override
    public boolean canHandle(String origin) {
        return getProperty().containsDelimiter(split(origin)[0]);
    }

    /**
     * Turns string to property (if string can be parsed go property)
     * @param origin - string representation of property
     * @return property, created from input string
     */
    public abstract Property toProperty(String origin);

    /**
     * Each factory returns its own property.
     * @return instance of property that is produced in the factory
     */
    protected abstract Property getProperty();

    /**
     *
     * @param string - input string
     * @return - splits string to field and value
     */
    protected String[] split(String string) {
        String[] strings = string.split(" ", 2);
        if (hasNotClosedBracket(strings[0])) {
            String[] toBrackets = strings[1].split("\"", 2);
            strings[0] += toBrackets[1];
            strings[0] += "\"";
            strings[1] = toBrackets[0];
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



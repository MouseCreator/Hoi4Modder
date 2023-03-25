package com.example.hoi4modder.model;

import java.util.ArrayList;

public class Lines {
    private ArrayList<String> lines;

    public Lines(String origin) {
        String[] originLines = origin.split("\n");
        for (String line : originLines) {
            if (line.isEmpty())
                continue;
            lines.add(line.trim().replaceAll("\t", ""));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int tabulation = 0;
        for (String line : lines) {
            if (line.contains("}"))
                tabulation--;
            builder.append("\t".repeat(tabulation)).append(line).append('\n');
            if (line.contains("{")) {
                tabulation++;
            }
        }
        return builder.toString();
    }

    public void removeAllContaining(String v) {
        ArrayList<String> copy = new ArrayList<>();
        for (String str : lines) {
            if (!str.contains(v)) {
               copy.add(str);
            }
        }
        this.lines = copy;
    }

    public void append(String s) {
        addLine(s);
    }

    public void appendFieldValue(String field,String value) {
        addLine(field + " = " + value);
    }

    private void addLine(String s) {
        this.lines.add(s);
    }
}

package com.example.hoi4modder.utilities;

public class Strings {
    public static boolean containsIgnoreCase(String str, String searchStr)     {
        String full = str.toLowerCase();
        String target = searchStr.toLowerCase();

        return full.contains(target);
    }
}

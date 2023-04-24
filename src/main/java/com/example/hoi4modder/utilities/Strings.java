package com.example.hoi4modder.utilities;

public class Strings {
    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        if (searchStr.isEmpty())
            return true;

        int length = searchStr.length();

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
}

package com.example.hoi4modder.utilities;

public class IntegerValues {
    public static int parseInt(String str) {
        if (str == null || str.isEmpty())
            return 0;
        return Integer.parseInt(str);
    }
}

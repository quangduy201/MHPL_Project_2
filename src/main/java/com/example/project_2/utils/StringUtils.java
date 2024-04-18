package com.example.project_2.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {
    public static String capitalize(String str) {
        if (str == null || str.isBlank()) {
            return null;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String capitalizeFully(String str) {
        if (str == null || str.isBlank()) {
            return null;
        }
        return Arrays.stream(str.split("\\s+"))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(" "));
    }
}

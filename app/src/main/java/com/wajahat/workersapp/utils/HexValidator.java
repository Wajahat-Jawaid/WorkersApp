package com.wajahat.workersapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexValidator {

    private static Pattern pattern;

    static {
        String hexPattern = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        pattern = Pattern.compile(hexPattern);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public static Boolean validate(String hex) {
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    public static String getValidHex(String hex) {
        if (hex.length() == 4)
            return "" + hex.charAt(0) + hex.charAt(1) + hex.charAt(1) + hex.charAt(2) +
                    hex.charAt(2) + hex.charAt(3) + hex.charAt(3);
        else if (hex.length() == 7) return hex;
        else return "#ffffff";
    }
}
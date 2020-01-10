package com.study.luxin.str;

import org.junit.Test;

public class StringRegexEscapeUtils {

    public static String escape(String regex) {
        return regex.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }

    private StringRegexEscapeUtils() {
    }


    public static void main(String[] args) {
        String a = "XXX" + "12345678-121".substring("12345678".length());
        System.out.println(a);
    }

}


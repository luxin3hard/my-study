package com.study.luxin.str;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;

public class StringEscapeUtilsDemo {

    @Test
    public void javaStrEscapeTest() {
        String str = "FC-LS111ASA((250801466)+++++";

        String ss = str.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");


        String sss  = str.replaceFirst(ss,"12121");



        System.out.println(sss);
    }
}

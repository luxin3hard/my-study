package com.study.luxin.code;

import org.junit.Test;

//https://leetcode-cn.com/problems/count-and-say/
public class Code38 {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String str = "1";

        String t = "";
        int count = 1;
        while (count++ < n) {
            int w = 1;
            char c = str.charAt(0);
            for (int i = 0; i < str.length(); ) {
                if (i + 1 < str.length() && c == str.charAt(i + 1)) {
                    i++;
                    w++;
                } else {
                    t = t + w + c;
                    if (++i < str.length()) {
                        c = str.charAt(i);
                        w = 1;
                    }
                }
            }
            str = t;
            t = "";
        }
        return str;
    }


    @Test
    public void test() {
        System.out.println(countAndSay(5));
      /*  System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));*/
    }

}

package com.study.luxin.code;

import org.junit.Test;

import java.util.Arrays;

public class Code344 {

    public void reverseString(char[] s) {
        if (s == null) {
            return;
        }

        int i = 0, j = s.length - 1;

        char t;
        while (i < j) {
            t = s[i];
            s[i] = s[j];
            s[j] = t;

            i++;
            j--;
        }
    }


    @Test
    public void test() {
        char[] chars = {'1', '2', '3', '4'};
        reverseString(chars);

        System.out.println(Arrays.toString(chars));
    }

}

package com.study.luxin.code;

import org.junit.Test;

//https://leetcode-cn.com/problems/implement-strstr/
public class Code28 {

    /**
     * 判断两个长度相同的字符串是否相同
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {

        int c = haystack.length() - needle.length();
        if (c < 0) {
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, needle.length() + i).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        assert strStr("011", "11") == 1;
    }


}

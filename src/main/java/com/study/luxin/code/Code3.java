package com.study.luxin.code;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Code3 {

    /**
     * 思路,p(i,j)是最长的串,如果i++的字符不在p(i,j)的范围内,那么最长串为p(i+1,j),如果s.charAt(i++)在p(i,j)的范
     * 围内,那么循环的串为p(i+1,position(s.charAt(i++))+1)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> char2Index = new HashMap();
        int max = 0;
        int j = 0;
        int tmpMax = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            Integer index = char2Index.get(c);
            if (index == null || index < j) {
                tmpMax++;
            } else {
                j = index + 1;
                tmpMax = i - j + 1;
            }
            if (tmpMax > max) {
                max = tmpMax;
            }
            char2Index.put(c, i);
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("ababca"));
    }

}

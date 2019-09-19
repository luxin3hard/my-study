package com.study.luxin.code;

import org.junit.Test;

import java.util.*;

//https://leetcode-cn.com/problems/group-anagrams/
public class Code49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        int[][] a = new int[1][26];
        Map<String, List<String>> tmp = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            for (char c : strs[i].toCharArray()) {
                a[0][c - 97] = a[0][c - 97] + 1;
            }
            String s = "";
            for (int j = 0; j < a[0].length; j++) {
                s = s + a[0][j] + (char) (j + 97);
                a[0][j] = 0;
            }

            List<String> stringList = tmp.get(s);
            if (stringList == null) {
                stringList = new ArrayList<>();
                stringList.add(strs[i]);
                tmp.put(s, stringList);
            } else {
                stringList.add(strs[i]);
            }
        }
        List<List<String>> result = new ArrayList<>();
        tmp.forEach((x, y) -> {
            result.add(y);
        });
        return result;
    }

    @Test
    public void test(){
        String[] strings = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strings));
    }
}

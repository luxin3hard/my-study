package com.study.luxin.code;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;

//https://leetcode-cn.com/problems/restore-ip-addresses/
public class Code93 {

    public List<String> restoreIpAddresses1(String s) {
        Set<Integer> set = new HashSet<>();
        set.add(3333);
        int l = s.length();
        if (l < 4) {
            return Collections.emptyList();
        }
        int k = 12 - l;
        for (int i = k; i > 0; i--) {
            Set<Integer> tmp = new HashSet<>();
            for (Integer v : set) {
                for (int j = 0; j < 4; j++) {
                    int r = ((int) Math.pow(10, j + 1));
                    int r1 = ((int) Math.pow(10, j));
                    int w = (v % r) / r1;
                    if (w - 1 > 0) {
                        tmp.add(v - r1);
                    }
                }
            }
            set = tmp;
        }
        return null;
    }


    @Test
    public void test() {

        //restoreIpAddresses("123456789010");

        restoreIpAddresses1("1234567890");


    }


    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>(); //保存最终的所有结果
        getAns(s, 0, new StringBuilder(), ans, 0);
        return ans;
    }

    private void getAns(String s, int start, StringBuilder tmp, List<String> ans, int count) {
        if (s.length() - start > 3 * (4 - count)) {
            tmp.delete(0, start + count);
            return;
        }


        for (int i = 1; i <= 3; i++) {
            String str = s.substring(start, start + i);

            if (Integer.valueOf(str) > 255 || str.startsWith("0")) {
                return;
            }

            if (count < 3) {
                tmp.append(str + ".");
            } else {
                tmp.append(str);
            }
            getAns(s, start + i, tmp, ans, count + 1);
        }

        if (count == 3) {
            ans.add(tmp.toString());
            tmp.delete(0, Integer.MAX_VALUE);
        }
    }


    @Test
    public void test1() {
        restoreIpAddresses("123123123123");
    }


}

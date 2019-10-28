package com.study.luxin.code;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;

//https://leetcode-cn.com/problems/restore-ip-addresses/
public class Code93 {

    public List<String> restoreIpAddresses(String s) {
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

        restoreIpAddresses("1234567890");


    }


}

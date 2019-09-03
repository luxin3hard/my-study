package com.study.luxin.code.greed;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class ActiveSelector {


    public void activeSelect(int[][] s, int i) {

        List<Integer> list = Lists.newArrayList();
        list.add(0);
        int t = 0;
        for (int j = 1; j < i; j++) {
            if (s[0][j] > s[1][t]) {
                list.add(j);
                t = j;
            }
        }

        System.out.println(list);
    }


    @Test
    public void testActiveSelect() {
        int[][] s = new int[][]{{1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12}, {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}
        };

        activeSelect(s, 11);
    }


}

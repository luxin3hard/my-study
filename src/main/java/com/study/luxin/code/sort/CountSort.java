package com.study.luxin.code.sort;

import org.junit.Test;

import java.util.Arrays;

public class CountSort {

    public void countSort(int[] a) {
        // 获取最大最小
        int max = a[0], min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }

        if (max == 1) {
            return;
        }

        //生成C,生成每个值得个数
        int[] c = new int[max + 1];

        for (int i = 0; i < a.length; i++) {
            c[a[i]] = c[a[i]] + 1;
        }

        //c[i]的值就是比i小的个数
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i - 1] + c[i];
        }

        int[] b = new int[a.length];
        //c[a[i]为a[i]最终的位置,因为有c[a[i]个元素小于等于a[i]
        for (int i = a.length - 1; i >= 0; i--) {
            b[c[a[i]] - 1] = a[i];
            c[a[i]] = c[a[i]] - 1;
        }

        System.out.println(Arrays.toString(b));
    }

    @Test
    public void test() {
        int[] a = new int[]{2, 5, 3, 0, 2, 3, 0, 3};
        countSort(a);
    }
}

package com.study.luxin.code;

import org.junit.Test;

//https://leetcode-cn.com/problems/powx-n/
public class Code50 {


    @Test
    public void test() {
        //0.00001
        //2147483647

        double x = 0.00001;
        int y = 2147483647;
        assert Math.pow(x, y) == myPow(x, y);
    }


    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        int t = Math.abs(n);
        String str = Integer.toBinaryString(t);

        int l = str.length();
        int[] p = new int[l];
        double[] r = new double[l];

        p[0] = 1;
        r[0] = x;

        for (int i = 1; i < l; i++) {
            p[i] = 2 * p[i - 1];
            r[i] = r[i - 1] * r[i - 1];
        }
        double result = 1D;
        char[] chars = str.toCharArray();
        int k = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '1') {
                result = result * r[k];
            }
            k++;
        }
        if (n < 0) {
            return 1 / result;
        } else {
            return result;
        }
    }

}

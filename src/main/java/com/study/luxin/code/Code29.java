package com.study.luxin.code;

import org.junit.Test;

//https://leetcode-cn.com/problems/divide-two-integers/

/**
 * 除法可以变成减法 这种时间复杂度是 n, 还可以用 二进制移位的方法时间复杂度是log(n)
 */
public class Code29 {

    public int divide(int dividend, int divisor) {
        long p = Math.abs((long) dividend);
        long q = Math.abs((long) divisor);

        if (p < q) {
            return 0;
        }
        long a = 0L;
        if (q == 1) {
            a = p;
        } else {
            while ((p = p - q) >= 0) {
                a++;
            }
        }

        if ((dividend < 0 && divisor < 0) || dividend > 0 && divisor > 0) {
            if (a > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return (int) a;
            }
        } else {
            return (int) -a;
        }
    }


    @Test
    public void test() {
        assert divide(-2147483648, -1) == 2147483647;
    }


}

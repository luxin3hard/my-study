package com.study.luxin.code.geektime.seven.day2;

import org.junit.Test;

public class MyRecursion {

    /**
     * n!
     */
    public int jcOpt(int n) {
        if (n == 1) {
            return 1;
        }
        return jcOpt(n - 1) * n;
    }

    @Test
    public void testJcOpt() {
        System.out.println(jcOpt(3));
    }


    public int climbStairs(int n) {

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }


        int t=1,w=2;
        int k;
        for (int i = 3; i <= n; i++) {
            k= w;
            w=w+t;
            t = k;
        }

        return w;
    }

    @Test
    public void test001(){
        System.out.println(climbStairs(4));
    }


}

package com.study.luxin.syn.myvolatile;

import org.junit.Test;

/**
 * volatile只能保证可见性和顺序性,
 */
public class VolatileNoAtm {
    private volatile int a = 0;

    @Test
    public void volatileNotSafe4Dependent() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    add();
                }
            }).start();
        }
        System.out.println(a);
    }

    private void add() {
        a++;
        int b = a;
    }
}

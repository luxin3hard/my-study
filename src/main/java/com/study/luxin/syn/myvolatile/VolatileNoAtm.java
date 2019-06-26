package com.study.luxin.syn.myvolatile;

import org.junit.Test;

/**
 * volatile只能保证可见性和顺序性,
 */
public class VolatileNoAtm {
    //----------------------------
    /**
     * 对单个变量的读和写都是原子性的
     * @return
     */
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
    //----------------------------

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

    // 复合操作是不具有原子性的
    private void add() {
        a++;
        int b = a;
    }




}

package com.study.luxin.syn.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 同一个类里面的方法加上synchronized,相当于 synchronized(this). 所以同一时间,只能访问一个synchronized的方法
 */
public class SynchronizedGetAndSet {

    public synchronized String getStr() throws InterruptedException {
        System.out.println("getStr start.");
        TimeUnit.SECONDS.sleep(1);
        return str;
    }

    public synchronized void setStr(String str) throws InterruptedException {
        System.out.println("setStr start.");
        TimeUnit.SECONDS.sleep(3);
        this.str = str;
        System.out.println("setStr end.");
    }

    private String str;


    @Test
    public void test() throws InterruptedException {
        new Thread(() -> {
            try {
                setStr("55");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            try {
                System.out.println(getStr());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(6);
    }


}

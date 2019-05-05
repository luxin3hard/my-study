package com.study.luxin.syn.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 同一个对象上可以设置多个锁,this的锁和 lock能同时进入,并不互斥
 */
public class ObjectLocks {
    private Object lock = new Object();

    public void objectLock1() throws InterruptedException {
        synchronized (this) {
            System.out.println("objectLock1 in");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("objectLock1 out");
        }
    }

    public void objectLock2() throws InterruptedException {
        synchronized (lock) {
            System.out.println("objectLock2 in");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("objectLock2 out");
        }
    }


    @Test
    public void test() throws InterruptedException {
        final ObjectLocks objectLocks = new ObjectLocks();

        Thread t1 = new Thread(() -> {
            try {
                objectLocks.objectLock1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            try {
                objectLocks.objectLock2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        TimeUnit.SECONDS.sleep(5);

    }

}

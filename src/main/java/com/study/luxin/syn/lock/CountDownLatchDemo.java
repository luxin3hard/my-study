package com.study.luxin.syn.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    // CountDownLatch的锁是共享锁, 如果锁可以获得,所有await()的线程都能获取锁.
    // count=0的时候,才能尝试获取锁,当大于零的时候,await的线程处于wait状态
    CountDownLatch countDownLatch = new CountDownLatch(10);

    @Test
    public void test() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.countDown();
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {


                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            try {
                System.out.println("in thread01-----");
                countDownLatch.await();
                System.out.println("thread01 done!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("in thread01-----");
                countDownLatch.await();
                System.out.println("thread02 done!!!!!!!!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

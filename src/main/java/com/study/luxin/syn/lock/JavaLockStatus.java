package com.study.luxin.syn.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class JavaLockStatus {

    ReentrantLock lock = new ReentrantLock();


    @Test
    public void test() throws InterruptedException {

        System.out.println("zhixing");

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(50);
            } catch (InterruptedException e) {
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(50);
            } catch (InterruptedException e) {


            }
        }).start();


        TimeUnit.SECONDS.sleep(100);
    }


}

package com.study.luxin.syn.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AQSDemo {

    @Test
    public void test() throws InterruptedException {
        Lock lock = new ReentrantLock();

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.setName("index: " + i);
            thread.start();
        }
        TimeUnit.SECONDS.sleep(200);
    }
}

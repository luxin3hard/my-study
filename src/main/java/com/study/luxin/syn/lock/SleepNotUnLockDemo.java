package com.study.luxin.syn.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep不会放弃锁,会相应中断
 */
public class SleepNotUnLockDemo {

    @Test
    /**
     * 线程sleep不会释放锁
     */
    public void sleepNotUnLock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("thread1111 sleep over!!!!");
            } catch (InterruptedException e) {
                System.out.println("sleep 相应中断");

            }
        });

        thread1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 running");
            lock.lock();
            System.out.println("thread2 locked------");
        });

        thread2.start();
        TimeUnit.SECONDS.sleep(3);
    }


    @Test
    // sleep,wait 都相应中断, 抛出异常后,不会自动释放锁.
    public void interrupt() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread thread1 = null;
        thread1 = getThread(lock, thread1);

        TimeUnit.SECONDS.sleep(1);

        Thread finalThread = thread1;
        Thread thread2 = new Thread(() -> {
            System.out.println("before interrupt");
            finalThread.interrupt();
            System.out.println("after interrupt");
        });

        thread2.start();
        TimeUnit.SECONDS.sleep(3);
    }

    private Thread getThread(ReentrantLock lock, Thread thread1) {
        try {
            thread1 = new Thread(() -> {
                lock.lock();
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("thread1111 sleep over!!!!");
                } catch (InterruptedException e) {
                    System.out.println("sleep 相应中断");
                    throw new RuntimeException(e);
                }
            });
            thread1.start();
        } catch (Exception e) {
            // 异常不能从线程抛出去,所以主线程,没办法抓到其他线程抛出的异常
            System.out.println("能抓住异常");
        }
        return thread1;
    }
}

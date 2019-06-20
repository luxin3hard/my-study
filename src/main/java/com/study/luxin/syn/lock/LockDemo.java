package com.study.luxin.syn.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * https://www.cnblogs.com/dolphin0520/p/3923167.html
 */
public class LockDemo {

    public void lockDemo() {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            // 执行具体操作需要用try-catch包围
        } finally {
            lock.unlock();
        }
    }


    // 因此当通过lockInterruptibly()方法获取某个锁时，如果不能获取到，只有进行等待的情况下，是可以响应中断的。
    // 而用synchronized修饰的话，当一个线程处于等待某个锁的状态，是无法被中断的，只有一直等待下去。
    // 必须抛出一样
    public void canInterruptLock() throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.lockInterruptibly();
        try {
        } finally {
            lock.unlock();
        }
    }


    // ----------------------------------------------------------------------------------------------------
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void getReadLock() throws InterruptedException {
        readWriteLock.readLock().lock();
        System.out.println("in read lock");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("out read lock-----");
        try {
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void getWriteLock() {
        readWriteLock.writeLock().lock();
        try {
        } finally {
            readWriteLock.writeLock().unlock();
            System.out.println("out write lock++++++++++++");
        }
    }

    /**
     * 如果读锁没有释放,写锁要等到读锁释放了,才能获取锁
     *
     * @throws InterruptedException
     */
    @Test
    public void readWriteLockTest() throws InterruptedException {
        LockDemo lockDemo = new LockDemo();
        new Thread(() -> {
            try {
                lockDemo.getReadLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> lockDemo.getWriteLock()).start();

        TimeUnit.SECONDS.sleep(3);
    }


    // ----------------------------------------------------------------------------------------------------
    // 三种情况下会返回 1.超时时间内获取了锁,返回true 2.当前线程在超时间内被中断 3.超时未获取到锁,返回false
    public void tryLockMethod() throws InterruptedException {
        Lock lock = new ReentrantLock();
        boolean canLock = lock.tryLock(1, TimeUnit.SECONDS);
        if (canLock) {
            try {
            } finally {
                lock.unlock();
            }
        }
    }

    // ----------------------------------------------------------------------------------------------------
    private Lock lock = new ReentrantLock();

    @Test
    public void newConditionMethod() throws InterruptedException {
        Condition condition = lock.newCondition();
        new Thread(() -> {
            System.out.println("before lock");
            lock.lock();
            System.out.println("after lock");
            try {
                System.out.println("after condition");
                // 只有获取了lock后才能调用 await() 方法
                condition.await();
                System.out.println("alive!!!!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread 01 after condition");
                // 必须获取了锁,才能释放锁
                condition.signal();
            } finally {
                lock.unlock();
            }

        }).start();
    }


    /**
     *
     */
    @Test
    public void reentrantReadWriteLockTest(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        // 同一个线程在获取写锁后,还能获取读线程
        lock.writeLock().lock();
        System.out.println("get write lock");
        lock.readLock().lock();
        System.out.println("get read lock");
        lock.writeLock().unlock();
        lock.readLock().unlock();

        //----------------------------------------------------------------

        // 即使是同个线程,在获取读线程后,也不能获取写线程,会被阻塞
        lock.readLock().lock();
        System.out.println("get read lock");
        lock.writeLock().lock();
        System.out.println("get write lock");
    }


}

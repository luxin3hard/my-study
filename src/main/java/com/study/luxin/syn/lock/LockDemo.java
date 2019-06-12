package com.study.luxin.syn.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
    // 必须抛出一样
    public void canInterruptLock() throws InterruptedException {
        Lock lock = new ReentrantLock();
        lock.lockInterruptibly();
        try {
        } finally {
            lock.unlock();
        }
    }

}

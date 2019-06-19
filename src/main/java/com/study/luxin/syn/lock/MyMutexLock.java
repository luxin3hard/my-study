package com.study.luxin.syn.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyMutexLock implements Lock {

    private volatile Syn syn;

    public MyMutexLock() {
        syn = new Syn();
    }


    @Override
    public void lock() {
        // 这个方法里面,如果获取不到锁,会将线程加入到队列里面
        syn.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        syn.acquireInterruptibly(0);
    }

    @Override
    public boolean tryLock() {
        return syn.tryAcquire(0);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        syn.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }


    private static class Syn extends AbstractQueuedSynchronizer {
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setState(1);
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            } else {
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
        }

    }
}



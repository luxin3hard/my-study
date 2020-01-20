package com.study.luxin.syn.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 多个线程能同时访问,使用共享锁
 *
 * 实际上release方式很复杂,只能拥有锁的线程才能释放锁,请参考
 * ReentrantReadWriteLock的#tryReleaseShared() 方法
 */
public class NewTwinsLock implements Lock {

    private Syn syn;


    public NewTwinsLock() {
        this.syn = new Syn();
    }

    public static void main(String[] args) {

        NewTwinsLock lock = new NewTwinsLock();

        lock.unlock();





    }

    @Override
    public void lock() {
        syn.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        syn.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        syn.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private static class Syn extends AbstractQueuedLongSynchronizer {

        public Syn() {
            setState(2);
        }


        @Override
        protected long tryAcquireShared(long arg) {
            for (; ; ) {
                long state = getState();
                long next = state - arg;
                if (next < 0 || compareAndSetState(state, next)) {
                    return next;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(long arg) {

            for (; ; ) {

                long state = getState();
                long next = state + arg;

                if(getSharedQueuedThreads().contains(Thread.currentThread())){

                }
                if (compareAndSetState(state, next)) {
                    return true;
                }
            }
        }
    }


}

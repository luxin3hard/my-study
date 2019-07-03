package com.study.luxin.syn.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {


    private Syn syn = new Syn(2);

    @Override
    public void lock() {

        syn.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unlock() {
        syn.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return syn.newCondition();
    }

    // 在外部无法直接使用 父类的 内部类
    private static final class Syn extends AbstractQueuedSynchronizer {

        Syn(int state) {
            setState(state);
        }

        @Override
        protected int tryAcquireShared(int arg) {

            for (; ; ) {

                int state = getState();

                int currentState = state - arg;

                if (currentState < 0 || compareAndSetState(state, currentState)) {
                    return currentState;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int state = getState();

                // 我觉得遗漏了一些细节,需要判断是否超出了
                if (state < 0 || state > 1) {
                    throw new IllegalStateException();
                }

                int newCount = state + arg;

                if (compareAndSetState(state, newCount)) {
                    return true;
                }
            }
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }


    }

}

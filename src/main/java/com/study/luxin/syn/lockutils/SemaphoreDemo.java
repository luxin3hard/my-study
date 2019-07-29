package com.study.luxin.syn.lockutils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
// TODO ASQ 实现方式给出
public class SemaphoreDemo {


    class SemaphoreResource<T> {
        private int num;
        private T resource;

        private volatile int count = 0;

        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public SemaphoreResource(int num, T resource) {
            this.num = num;
            this.resource = resource;
        }


        public T useResource() throws InterruptedException {
            lock.lock();

            for (; ; )
                if (count < num) {
                    try {
                        count++;
                        return resource;
                    } finally {
                        lock.unlock();
                    }
                } else {
                    condition.await();
                }
        }


        public void release() {
            lock.lock();
            try {
                count--;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }


}

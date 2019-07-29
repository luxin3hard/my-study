package com.study.luxin.syn.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockingQueue {

    int[] array;
    int index = -1;


    public MyArrayBlockingQueue() {
    }

    MyArrayBlockingQueue(int size) {
        this.array = new int[size];
    }

    ReentrantLock lock = new ReentrantLock();
    Condition fullCondition = lock.newCondition();
    Condition emptyCondition = lock.newCondition();


    public void put(int val) throws InterruptedException {
        lock.lock();

        try {
            if (index >= array.length) {
                fullCondition.await();
            }

            if (index < 0) {
                emptyCondition.signalAll();
            }
            array[index++] = val;
        } finally {
            lock.unlock();
        }
    }


    public int take() throws InterruptedException {
        lock.lock();

        try {
            if (index < 0) {
                emptyCondition.await();
            }

            int a = array[0];
            for (int i = 0; i < array.length - 1; i++) {
                array[i] = array[i + 1];
            }

            if (index-- == array.length - 1) {
                fullCondition.signalAll();
            }
            return a;
        } finally {
            lock.unlock();
        }

    }


    @Test
    public void test() throws InterruptedException {
        ttt:
        for (; ; ) {
            System.out.println(111);

            TimeUnit.SECONDS.sleep(2);
            break ttt;
        }
    }


}

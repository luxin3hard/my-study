package com.study.luxin.syn.connect;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Thread.join()实现功能: 当前线程等待Thread.join()的线程执行完成后,才能执行.
 */
public class ThreadJoin {
    @Test
    public void threadJoinDemo() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
                System.out.println("join done!!!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        Thread thread1 = new Thread(new Runner(thread));

        thread1.start();
        TimeUnit.SECONDS.sleep(6);
    }

    static class Runner implements Runnable {
        Thread thread;

        public Runner(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                System.out.println("in runner");
                thread.join();
                System.out.println("out runner");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

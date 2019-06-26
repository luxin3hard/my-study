package com.study.luxin.syn.lockutils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.LocalTime.now;

public class CyclicBarrierDemo {

    /**
     * 循环的使用cyclicBarrier.
     * 只有调用await() 达到数量,线程才能停止阻塞
     */
    public void test() {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程都执行完了！");
            }
        });

        ExecutorService exec = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {//循环10次试试
            barrier.reset();
            Task t1 = new Task(barrier);
            Task t2 = new Task(barrier);

            exec.submit(t1);
            exec.submit(t2);
            try {
                barrier.await();
                System.out.println(now() + "一次循环已经搞定啦。");
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        }
        exec.shutdown();
    }


    class Task implements Runnable {
        private CyclicBarrier barrier;

        public Task(CyclicBarrier cyclicBarrier) {
            barrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.study.luxin;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            4, 20, 100L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());


    @Test
    /**
     * execute 会在执行线程抛出异常,从外部线程不能捕获到异常,会导致线程池里面的线程死亡.   即不会影响外部线程.
     */
    public void executeTest() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println("--------1");
                throw new RuntimeException();
            });
        }

        // 异常不会影响 外部的线程
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\n\n\n\n");
        System.out.println("现在的线程数: " + executor.getPoolSize());
        System.out.println("外部线程正常--------------------------");
    }


    @Test
    /**
     * 默认不会抛出异常, 通过get方法能获得异常
     */
    public void submit() throws InterruptedException, ExecutionException {
        Future<Object> submit = executor.submit(() -> {
            throw new RuntimeException();
        });

        submit.get();
        TimeUnit.SECONDS.sleep(1);
    }


    @Test
    /**
     * 线程不能start两次, 第一次调用start方法会抛出异常
     */
    public void test001() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread());

        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.start();
        TimeUnit.SECONDS.sleep(2);
    }


}

package com.study.luxin;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1, 1, 100L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.CallerRunsPolicy());


    @Test
    /**
     * execute 会在执行线程抛出异常,从外部线程不能捕获到异常,会导致线程池里面的线程死亡.   即不会影响外部线程.
     */
    public void executeTest() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                System.out.println("--------1");
                throw new RuntimeException();

            });
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println("11");


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
}

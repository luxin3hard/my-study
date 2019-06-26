package com.study.luxin.syn;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JStackUse {

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue());


    /**
     * 线程在sleep的时候,线程的状态是 time_wait状态.
     * <p>
     * wait和time_wait状态是等待通知或是中断,才能进入到runnable
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(new MyTask());
        }

        TimeUnit.SECONDS.sleep(3000);
    }

    class MyTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    TimeUnit.SECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

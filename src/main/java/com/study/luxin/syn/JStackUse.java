package com.study.luxin.syn;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JStackUse {

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue());


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
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}

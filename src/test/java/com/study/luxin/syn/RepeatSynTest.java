package com.study.luxin.syn;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lx on 02/08/2018.
 */
public class RepeatSynTest {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 4, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    @Test
    public void repeatSynchronizedTest() throws InterruptedException {

        Father son = new Son();

        threadPoolExecutor.execute(() -> {
            try {
                son.doSomething();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(1);
        son.doSomething();

    /*    Father father = new Father();

        threadPoolExecutor.execute(() -> {
            try {
                System.out.println("father thread---");
                father.doSomething();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/


        TimeUnit.SECONDS.sleep(10);

    }

}

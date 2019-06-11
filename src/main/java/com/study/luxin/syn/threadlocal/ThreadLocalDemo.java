package com.study.luxin.syn.threadlocal;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {
    @Test
    public void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(LocalClazz.getThreadLocalValue());

            LocalClazz.setTheadLocal("luxin----");
            System.out.println(LocalClazz.getThreadLocalValue());
        });

        t1.start();


        // t1和t2的变量互相不干扰
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LocalClazz.getThreadLocalValue());

            LocalClazz.setTheadLocal("+++++++++");
            System.out.println(LocalClazz.getThreadLocalValue());
        });
        t2.start();

        TimeUnit.SECONDS.sleep(1);
    }


}

package com.study.luxin.syn.myvolatile;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * volatile使用条件有两个(实际都变量前后是否有依赖关系):
 * 1.变量前后没有依赖关系
 * 2.变量不包含在不变式中
 */
public class VolatileInvariants {

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 2000, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private volatile int a = -1;


    public void setA(int a) {
        this.a = a;
    }

    // 变式方法
    public void variantMethod() {
        if (a < 0) {
            // 执行和a相关的任何方法都是变式
            System.out.println("a应该小于0");
            if (a > 0) {
                System.out.println("写变化了++++++++++++\n");
            }
        }
    }


    @Test
    public void variantTest() throws InterruptedException {
        Thread thread1 = new Thread(this::variantMethod);
        Thread thread2 = new Thread(() -> {
            setA(1);
        });

        thread1.start();
        thread2.start();
        TimeUnit.MILLISECONDS.sleep(1);
    }


    @Test
    public void test() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            new VolatileInvariants().variantTest();
        }
    }

}

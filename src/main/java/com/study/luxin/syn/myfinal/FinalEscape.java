package com.study.luxin.syn.myfinal;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class FinalEscape {

    static FinalEscape obj;
    final int a;

    public FinalEscape() {
        System.out.println("初始化开始!");
        obj = this;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a = 6;

        System.out.println("初始化完成!!!");
    }


    @Test
    /**
     * final能保证在使用的时候,已经被初始化了.
     * 但是在构造器中,逸出,还会导致问题. 能看到没有初始化的数据
     *
     * 从下面的方法,可以看到,会打印出来a=0的数据
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int c = 0;
            while (true) {
                if (c++ % 100000 == 0) {

                    System.out.println("测试22 start");
                    if (obj != null) {
                        System.out.println("+++ " + obj.a);
                    }
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> new FinalEscape()).start();
        TimeUnit.SECONDS.sleep(10);
    }


}

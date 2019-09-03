package com.study.luxin.syn.methodstack;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImmutableSafeClazz {

    public void readList(List<String> list) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(list);
    }


    public void cleanList(List<String> list) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("before clean:" + list);
        list.clear();
        System.out.println("after clean:" + list);
    }


    @Test
    /**
     * 无状态类,是指类没有域只有方法. 而且方法可以共享变量,但是不能改变共享变量.
     *
     * 如下方法,read 和 clean 两个方法共享了变量,但是改变了变量,不是无状态的类,线程不安全.
     */
    public void test() throws InterruptedException {
        List<String> list = Lists.newArrayList("1", "2");
        new Thread(() -> {
            try {
                cleanList(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                readList(list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(4);
    }


}

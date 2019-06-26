package com.study.luxin.syn;

/**
 * 线程优先级,1-10,越大获取越多的时间片,默认值是5.
 * 减少运行时间短的线程优先级,提高频繁阻塞的线程的优先级
 */
public class PriorityDemo {

    // 不同的操作系统,对线程优先级的处理不一样,可能会不生效
    public void test() {
        Thread thread = new Thread(() -> {
        });
        thread.setPriority(10);
        thread.start();
    }
}

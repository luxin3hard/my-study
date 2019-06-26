package com.study.luxin.syn;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程,主要用来做后台调度或是做支持线程.
 * jvm没有非daemon线程了,守护线程将会退出.
 * <p>
 * 特点: 1.daemon线程创建的线程是daemon的. 2.守护线程的finally不一定会执行.
 * <p>
 * 使用: 需要在thread.start()之前设置是否为daemon状态
 */
public class DaemonDemo {
    /**
     * 主线程退出了,守护线程的 finally没有执行
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}

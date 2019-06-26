package com.study.luxin.syn.threadlocal;

public class InheritableThreadLocalDemo {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    /**
     * main线程就是 新建的thread的父线程, 会复制父线程的 daemon和priority 属性 和inheritableThreadLocal属性
     *
     * @param args
     */
    public static void main(String[] args) {
        threadLocal.set(1000);
        inheritableThreadLocal.set("--2-3-423");

        Thread thread = new Thread(() -> {
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
        });
        thread.start();
    }
}

package com.study.luxin.syn.threadlocal;

public class InheritableThreadLocalDemo {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    /**
     * main线程就是 新建的thread的父线程, 会复制父线程的 daemon和priority 属性 和inheritableThreadLocal属性 和 contextClassLoader
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

        // 自定义的线程最好添加名字,方便定位问题
        thread.setName("luxTestThread");
        thread.start();
    }
}

package com.study.luxin.syn.threadlocal;

/**
 * ThreadLocal类的作用是维护线程的变量,多个线程之间是隔离的
 */
public class LocalClazz {

    private static ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "hello threadLocal!!!");

    public static String getThreadLocalValue() {
        return stringThreadLocal.get();
    }

    public static void setTheadLocal(String string) {
        stringThreadLocal.set(string);
    }

}

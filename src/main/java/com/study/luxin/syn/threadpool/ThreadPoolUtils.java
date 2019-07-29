package com.study.luxin.syn.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolUtils {
    // private static Logger logger = LoggerFactory.getLogger(ThreadPoolUtils.class);

    private static ThreadPoolExecutor threadPoolExecutor;

    static {
        final ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
        /**
         * 如果没有 un-catch 异常处理,会打印出来堆栈,线程同样会死.
         *
         * 异常处理handler会在线程死后,记录信息
         */
        final ThreadFactory factory = builder.setDaemon(false)
                .setNameFormat("ThreadPoolUtils-%d")
                //.setUncaughtExceptionHandler((t, e) -> System.out.println("luxin------"))
                .build();
        threadPoolExecutor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() * 2,
                32,
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(500),
                factory
        );
    }

    public ThreadPoolUtils() {
    }

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }


    @Test
    public void test() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        threadPoolExecutor.execute(() -> {
            throw new RuntimeException("dsdfsdfsd");
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println(threadPoolExecutor.getActiveCount());
    }

}

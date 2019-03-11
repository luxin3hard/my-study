package com.study.luxin;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Hello world!
 */
public final class App {

    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("thread run()");

        }
    }

    class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("runnable run()");
        }
    }

    class MyCallable implements Callable{
        @Override
        public Object call() throws Exception {
            System.out.println("callable run()");
            return "abc";
        }
    }


    @Test
    public void threadTest() throws InterruptedException, ExecutionException {
        Thread thread = new MyThread();
        thread.start();
        ExecutorService executorService =  new ThreadPoolExecutor(1,1,100L, TimeUnit.SECONDS, new LinkedBlockingQueue());

        //
        Runnable runnable = new MyRunnable();
        executorService.execute(runnable);

        //
        Callable callable = new MyCallable();
        Future submit1 = executorService.submit(callable);

        System.out.println(submit1.get());

        //
        FutureTask<Object> futureTask = new FutureTask(callable);
        Future<?> submit = executorService.submit(futureTask);

        TimeUnit.SECONDS.sleep(1);

        System.out.println(submit.get());

        TimeUnit.SECONDS.sleep(11);
    }


}

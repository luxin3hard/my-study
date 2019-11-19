package com.study.luxin.theadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class MyThreadPoolUtils {
    public static final int MAX_PARALLEL_NUM = 200;
    private static ExecutorService executorService;
    private static ExecutorService backgroundExecutorService;

    static {
        ThreadFactory workerFactory = new ThreadFactoryBuilder()
                .setNameFormat("ParallelUtils-%d").setDaemon(true).build();
        executorService = new ThreadPoolExecutor(50, 200, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(50),
                workerFactory);

        ThreadFactory backgroundWorkerFactory = new ThreadFactoryBuilder()
                .setNameFormat("ParallelUtils-Background-%d").setDaemon(true).build();
        backgroundExecutorService = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(500),
                backgroundWorkerFactory);
    }


    public static ParallelTask createParallelTask() {
        return new ParallelTaskImpl(executorService);
    }

    public static ParallelTask createBackgroundTask() {
        return new ParallelTaskImpl(backgroundExecutorService);
    }

    public static void main(String[] args) throws Exception {
        boolean ret = createParallelTask()
                .submit(() -> {
                    System.out.println("1");
                    throw new RuntimeException("test");
                })
                .submit(() -> System.out.println("2"))
                .await(100, TimeUnit.MILLISECONDS);

        System.out.println(ret);

    }

    public interface ParallelTask {

        ParallelTask submit(Runnable runnable);

        boolean await(long timeout, TimeUnit timeUnit) throws TimeoutException;

        void run();

    }

    private static class ParallelTaskImpl implements ParallelTask {

        private List<Runnable> runnableList = new ArrayList<>(MAX_PARALLEL_NUM);

        private ExecutorService executor;

        private Map<String, String> mdcMap;


        public ParallelTaskImpl(ExecutorService executor) {
            this.executor = executor;
            getMDC();
        }

        private void getMDC() {
            try {
                mdcMap = MDC.getCopyOfContextMap();
            } catch (Exception e) {
                log.warn("getMDC", e);
            }
        }

        private void putMDC() {
            try {
                MDC.setContextMap(mdcMap);
            } catch (Exception e) {
                log.warn("putMDC", e);
            }
        }

        private void clearMDC() {
            try {
                MDC.clear();
            } catch (Exception e) {
                log.warn("clearMDC", e);
            }
        }


        @Override
        public ParallelTask submit(Runnable runnable) {

            if (runnable != null) {
                if (runnableList.size() <= MAX_PARALLEL_NUM) {
                    runnableList.add(runnable);
                } else {
                    throw new RuntimeException("Max Parallel Task Number:" + MAX_PARALLEL_NUM);
                }
            }

            return this;
        }

        @Override
        public boolean await(long timeout, TimeUnit timeUnit) throws TimeoutException {
            final AtomicBoolean ret = new AtomicBoolean(true);

            if (runnableList.isEmpty()) {
                return true;
            }
            CountDownLatch countDownLatch = new CountDownLatch(runnableList.size());

            for (Runnable runnable : runnableList) {
                executor.submit(() -> {
                    try {
                        putMDC();
                        runnable.run();
                    } catch (Exception e) {
                        ret.compareAndSet(true, false);
                        log.error("execute task error", e);
                    } finally {
                        countDownLatch.countDown();
                        clearMDC();
                    }
                });
            }

            try {
                // 即使是超时了,线程池 还是会继续执行任务
                boolean finished = countDownLatch.await(timeout, timeUnit);
                if (!finished) {
                    throw new TimeoutException("execute task timeout");
                }
            } catch (InterruptedException e) {
                throw new TimeoutException("execute task interrupted");
            }

            return ret.get();
        }


        @Override
        public void run() {
            if (runnableList.isEmpty()) {
                return;
            }

            for (Runnable runnable : runnableList) {
                try {
                    executor.submit(() -> {
                        try {
                            putMDC();
                            runnable.run();
                        } catch (Exception e) {
                            log.error("execute task error", e);
                        } finally {
                            clearMDC();
                        }
                    });
                } catch (Exception e) {
                    log.error("submit task error!", e);
                }
            }
        }
    }


}

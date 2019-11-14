package com.study.luxin.log;

import org.slf4j.MDC;

import java.util.Map;

public class MyTask implements Runnable {
    private Map<String, String> mdcMap;
    private Runnable runnable;

    public MyTask(Runnable r) {
        mdcMap = MDC.getCopyOfContextMap();
        runnable = r;
    }

    @Override
    public void run() {
        MDC.setContextMap(mdcMap);
        // 这样在线程内的方法,就能共享mdc数据了

        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MDC.clear();
        }
    }
}

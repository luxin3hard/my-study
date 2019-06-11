package com.study.luxin.syn;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by lx on 02/08/2018.
 */
public class Son extends Father {

    @Override
    public synchronized void doSomething() throws InterruptedException {
        System.out.println("Son done.");
        //TimeUnit.SECONDS.sleep(3);
        super.doSomething();
    }
}

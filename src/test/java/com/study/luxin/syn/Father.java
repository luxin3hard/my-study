package com.study.luxin.syn;

import java.util.concurrent.TimeUnit;

/**
 * Created by lx on 02/08/2018.
 */
public class Father {

    public synchronized void doSomething() throws InterruptedException {
        System.out.println("father do."+this);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("father done."+this);
    }


}

package com.study.luxin.deginpattern.singleton;

/**
 * Created by luxin on 2017/11/4.
 */
public enum EnumSingleton {
    INSTANCE;
    private Singleton singleton;


    public void EnumSingleton() {
        singleton = new Singleton();
    }


    public Singleton getSingleton() {
        return INSTANCE.singleton;
    }

    public class Singleton {
    }

}


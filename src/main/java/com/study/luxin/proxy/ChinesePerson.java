package com.study.luxin.proxy;

public class ChinesePerson implements Person {
    @Override
    public String speak() {
        return "Chinese";
    }

    @Override
    public void doSomething() {
        System.out.println("chinese done!");
    }
}

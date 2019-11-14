package com.study.luxin.deginpattern.strategy;

public class Context {

    private CalculateStrategy calculateStrategy;

    public Context(CalculateStrategy calculateStrategy) {
        this.calculateStrategy = calculateStrategy;
    }


    public int calculate(int a, int b) {
        return calculateStrategy.calculate(a, b);
    }

}

package com.study.luxin.deginpattern.strategy;

public class AddStrategy implements CalculateStrategy {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}

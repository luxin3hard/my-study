package com.study.luxin.deginpattern.strategy;

/**
 * https://www.jianshu.com/p/a7d0abfd0cb9
 */
public class MyCalculator {

    public static void main(String[] args) {
        CalculateStrategy calculateStrategy = CalculateFactory.createCalculateOpt("+");
        assert calculateStrategy.calculate(11, 3) == 14;
    }
}

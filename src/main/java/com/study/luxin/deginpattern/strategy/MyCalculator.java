package com.study.luxin.deginpattern.strategy;

/**
 * https://www.jianshu.com/p/a7d0abfd0cb9
 */
public class MyCalculator {

    public static void main(String[] args) {


        CalculateStrategy calculateStrategy = CalculateFactory.createCalculateOpt("+");
        Context context = new Context(calculateStrategy);
        assert context.calculate(11, 3) == 14;
    }
}

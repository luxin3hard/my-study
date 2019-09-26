package com.study.luxin.deginpattern.strategy;

public class CalculateFactory {

    public static CalculateStrategy createCalculateOpt(String opt) {
        if ("+".equals(opt)) {
            return new AddStrategy();
        } else if ("-".equals(opt)) {
            return new SubtractStrategy();
        }

        return null;
    }

}

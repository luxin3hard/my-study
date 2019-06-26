package com.study.luxin.syn.myfinal;

import org.junit.Test;

public class FinalDemo {

    private int a = 0;
    private final int b = a;

    // 如果域字段是static,必须使用static进行初始化
    // final在使用前,一定被赋值过了
    // private static  final int b = a;

    public FinalDemo() {

        System.out.println("sdas:" + b);
    }

    public static void main(String[] args) {
        new FinalDemo();
    }

}

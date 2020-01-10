package com.study.luxin.syn.myfinal;

import org.junit.Test;

public class FinalDemo {

    private int a = 0;
    private final int b = a;

    // 如果域字段是static,必须使用static进行初始化
    // final在使用前,一定被赋值过了
    // private static  final int b = a;

    // final保证被使用之前,已经被正确初始化了; 普通的变量不会有这个保证

    public FinalDemo() {

        System.out.println("sdas:" + b);
    }

    public static void main(String[] args) {
        new FinalDemo();
    }

}

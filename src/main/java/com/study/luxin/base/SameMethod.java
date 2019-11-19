package com.study.luxin.base;

import org.junit.Test;

public class SameMethod implements MethodDefault {

    public void mt() {
        System.out.println("child");
    }



    @Test
    public void test(){

        this.mt();

    }
}

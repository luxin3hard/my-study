package com.study.luxin.base;

import com.study.luxin.classload.Father;
import com.study.luxin.classload.Son;
import org.junit.Test;

public class ClassMethod {

    @Test
    public void isAssignableFromUse() {
        // 是否是参数的父类或是相同类型
        System.out.println(Father.class.isAssignableFrom(new Son().getClass()));

        System.out.println(Son.class.isAssignableFrom(new Father().getClass()));
        System.out.println(new Son() instanceof Object);



    }

}

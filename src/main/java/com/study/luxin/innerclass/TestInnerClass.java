package com.study.luxin.innerclass;

public class TestInnerClass {
    NormalClass normalClass = new NormalClass();

    public void test() {
        // normalClass 是 InnerClazzDemo 的子类,InnerClass是InnerClazzDemo的内部类,子类只能载子类内部使用父类的内部类
        // 子类对象,无法直接使用父类的内部类
        // new NormalClass.InnerClass();
    }
}
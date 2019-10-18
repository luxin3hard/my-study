package com.study.luxin.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibPersonHandler<T> implements MethodInterceptor {
    private T person;

    public CglibPersonHandler(T person) {
        this.person = person;
    }

    //生成代理对象
    public T createCgLibProxy() {
        //工具类
        Enhancer enhancer = new Enhancer();
        //设置被代理的对象，也可以理解为设置父类，因为动态代理类是继承了被动态代理类。
        enhancer.setSuperclass(person.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类的动态代理类对象
        return (T) enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before----------");
        Object invoke = method.invoke(person, objects);
        System.out.println("after-----------");
        return invoke;
    }
}

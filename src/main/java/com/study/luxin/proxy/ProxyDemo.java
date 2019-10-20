package com.study.luxin.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * 非常不错的博客
 * https://www.jianshu.com/p/e10a218d6f35
 */
public class ProxyDemo {

    @Test
    /**
     * 静态代理方法
     * 静态代理虽然实现简单且容易理解，但由于代理类持有目标对象的引用，
     * 使得代理对象与目标对象耦合在一起，一个代理类不能作用于多个目标对象。
     * 如果一个接口下有多种实现，而每种实现都需要代理的话，就需要重新写代理类，不能重用代码。
     */
    public void staticProxy() {
        PersonStaticPoxy poxy = new PersonStaticPoxy(new ChinesePerson());
        poxy.doSomething();
        poxy.speak();
    }


    @Test
    public void javaProxy() {
        PersonHandler<Person> personHandler = new PersonHandler<>(new ChinesePerson());
        Person person = personHandler.getPersonProxy();
        assert Objects.equals(person.speak(), "Chinese");
        // 这个是生成代理对象
        Person person1 = (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), personHandler);
    }


    @Test
    public void cglibProxy() {
        CglibPersonHandler<Person> handler = new CglibPersonHandler<>(new ChinesePerson());
        Person cgLibProxy = handler.createCgLibProxy();
        assert Objects.equals(cgLibProxy.speak(), "Chinese");
    }


}

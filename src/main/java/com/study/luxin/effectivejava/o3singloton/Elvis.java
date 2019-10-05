package com.study.luxin.effectivejava.o3singloton;

/**
 * 使用静态final的域来实现单例,私有构造器能保证全局唯一.
 *
 * 问题:
 * 1. 虽然构造器是私有的,可以通过反射AccessibleObject.setAccessible,实现能调用构造器,导致出现新的实例. (解决方法,如果发现INSTANCE 不为空,那么调用私有构造方法,抛出异常)
 * 2. 下面的两种方式,如果 Elvis实现了Serializable,在反序列化,都会导致INSTANCE变成新的,破坏单例模式.  可以通过 readResolve()来解决.
 */
public class Elvis {

    // 公有域的实现方式.
    public static final Elvis INSTANCE = new Elvis();

    private Elvis(){
    }


    // 静态工厂实现方式
    /**
     * 相比静态域的优势:
     * 1.我们可以很容易改变是否返回的是单例的想法. 比如,将getInstance 每次都修改成返回new Elvis(),变成不是单例的.
     *
     */
    private static final Elvis INSTANCE01 = new Elvis();
    public static Elvis getInstance(){
        return INSTANCE01;
    }






}

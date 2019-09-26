package com.study.luxin.deginpattern.singleton;

/**
 * Created by luxin on 2017/11/5. 不过还需要考虑一种情况，假如两个线程A、B，A执行了if (instance == null)语句，
 * 它会认为单例对象没有创建，此时线程切到B也执行了同样的语句，B也认为单例对象没有 创建，然后两个线程依次执行同步代码块，并分别创建了一个单例对象。为了解决这个问 题，还需要在同步代码块中增加if
 * (instance == null)语句，也就是上面看到的代码.
 *
 *
 * 这里要提到Java中的指令重排优化。所谓指令重排优化是指在不改变原语义的情况下，通 过调整指令的执行顺序让程序运行的更快。JVM中并没有规定编译器优化相关的内容，也就
 * 是说JVM可以自由的进行指令重排序的优化。 这个问题的关键就在于由于指令重排优化的存在，导致初始化Singleton和将对象地址赋给instance
 * 字段的顺序是不确定的。在某个线程创建单例对象时，在构造方法被调用之前，就为该对象分配了内存 空间并将对象的字段设置为默认值。此时就可以将分配的内存地址赋值给instance字段了，然而该对象
 * 可能还没有初始化。若紧接着另外一个线程来调用getInstance，取到的就是状态不正确的对象，程序就会出错。 以上就是双重校验锁会失效的原因，不过还好在JDK1.5及之后版本增加了volatile关键字。volatile
 * 的一个语义是禁止指令重排序优化，也就保证了instance变量被赋值的时候对象已经是初始化过的，从而避免了上面说到的问题。
 *
 * @see io.netty.handler.timeout.ReadTimeoutException
 */
public class DoubleCheckSynchronizedSingleton {

    private static volatile DoubleCheckSynchronizedSingleton instance;

    private DoubleCheckSynchronizedSingleton() {
    }

    public static DoubleCheckSynchronizedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSynchronizedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSynchronizedSingleton();
                }
                return instance;
            }
        } else {
            return instance;
        }
    }


}

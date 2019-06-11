package com.study.luxin.syn.myvolatile;

/**
 * 推荐使用volatile的场景
 */
public class VolatileUsageScene {

    //----------作为状态标志---------------
    // 前后没有依赖关系
    private boolean stop = false;

    public void stopWhileCycle() {
        while (!stop) {
            //doSomething() 和stop无关的事情
        }
    }

    public void setStop() {
        stop = true;
    }
    //----------------------------------


    //--------------单例模式--------------------
    // 这个例子不是很恰当,实际还是使用了锁保证了线程安全,volatile可以保证顺序性,如果没有 //--3,在没有完成构造的时候,其他对象就能获得这个引用
    // 加入volatile保证了代码的顺序性,获得的instance都是可以的对象
    private static volatile VolatileInvariants instance;

    public static VolatileInvariants getInstance() {
        if (instance == null) {
            synchronized (VolatileUsageScene.class) {        //--3
                if (instance == null) {
                    instance = new VolatileInvariants();
                }
            }
        }
        return instance;
    }
    //-------------------------------------------


    //-----------------定期 “发布” 观察结果供程序内部使用--------------------------
    public volatile String lastUser; //发布的信息

    public boolean authenticate(String user, String password) {
        boolean valid = passwordIsValid(user, password);
        if (valid) {
            // 仅仅用于发布至,供其他线程读取这个数值
            lastUser = user;
        }
        return valid;

    }

    private boolean passwordIsValid(String user, String password) {
        return true;
    }
    //-----------------------------------------------------------------------


    //------------开销较低的 读-写锁策略------------------------------------
    // volatile允许多个读,写会线程不安全,所以写通过锁保护
    // 如果在写操作不多的情况下,性能优于 无锁竞争
    private volatile int value;

    // 读操作，没有synchronized，提高性能
    public int getValue() {
        return value;
    }

    // 写操作，必须synchronized。因为x++不是原子操作
    public synchronized int increment() {
        return value++;
    }

}

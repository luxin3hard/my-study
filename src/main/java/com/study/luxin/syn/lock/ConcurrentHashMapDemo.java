package com.study.luxin.syn.lock;

import java.util.concurrent.ConcurrentHashMap;

// ConcurrentHashMap key value 都不能为 null,如果为null 会抛出异常
// ConcurrentHashMap不会对整个容器进行扩容，而只 对某个segment进行扩容
// ConcurrentHashMap的做法是先尝试2次通过不锁住Segment的方式来统计各个Segment大小，
// 如 果统计的过程中，容器的count发生了变化，则再采用加锁的方式来统计所有Segment的大小
public class ConcurrentHashMapDemo {

    ConcurrentHashMap map = new ConcurrentHashMap();

}

package com.study.luxin.collection;

import org.junit.Test;

import java.util.WeakHashMap;

public class WeakHashMapDemo {
    WeakHashMap<String, Object> weakHashMap = new WeakHashMap<>();

    @Test
    /**
     * 如果key没有强引用,垃圾回收就会回收
     */
    public void keyNotStrongRef() {
        for (int i = 0; i < 10; i++) {
            weakHashMap.put(new String("123"), new int[10][10]);
        }

        System.gc();
        assert weakHashMap.get(new String("123")) == null;
        //如果key没有被强引用, value就会被回收
        assert weakHashMap.get("123") == null;
    }


    @Test
    /**
     * key有强引用,gc也不会回收
     */
    public void strongRef() {
        String str = new String("123");
        weakHashMap.put(str, new int[10][10]);
        System.gc();
        assert weakHashMap.get("123") != null;
    }

}

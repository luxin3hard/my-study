package com.study.luxin.java8;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

public class MapMergeDemo {

    @Test
    public void test() {
        Map<String, String> map = Maps.newHashMap();

        // 如果key为空或是value为null,直接将key对应的value赋值为下面的value
        map.merge("123", "--1", (x, y) -> x + y);

        System.out.println(map.values());

        // 如果不为空,就将老的value和下面给的value做函数处理
        map.merge("123", "--1", (x, y) -> x + y);
        System.out.println(map.values());

        map.computeIfAbsent("lx", x -> x+" love hxz");
        System.out.println(map.values());
    }
}

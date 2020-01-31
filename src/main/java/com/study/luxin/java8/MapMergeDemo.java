package com.study.luxin.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapMergeDemo {

    @Test
    public void test() {
        Map<String, String> map = Maps.newHashMap();

        // 如果key为空或是value为null,直接将key对应的value赋值为下面的value
        map.merge("123", "--1", (x, y) -> x + y);

        System.out.println(map.values());

        // 如果不为空,就将老的value和下面给的value做函数处理
        map.merge("123", "--1", (old, newV) -> old + newV);
        System.out.println(map.values());

        map.computeIfAbsent("lx", x -> x + " love hxz");
        System.out.println(map.values());
    }


    @Test
    public void test1() {

        Map<String, String> map = Maps.newHashMap();
        map.put("a", "b");
        map.forEach((k, v) -> v = "100");

        System.out.println(map);

    }


    //--------------------------------------------------------------------------------
    @Test
    public void toMapTest() {
        List<String> list = Lists.newArrayListWithCapacity(5);

        list.add(null);
        list.add("luxin");

        list.stream().collect(Collectors.toMap(k -> k, k -> k.length()));
    }


}

package com.study.luxin.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class CompareDemo {

    @Test
    public void test() {
        List<String> list = Lists.newArrayList("121", "32");
        list.sort(Comparator.comparingInt(String::length));

        System.out.println(list);
    }





}

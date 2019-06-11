package com.study.luxin.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class FindFirst {


    @Test
    public void test() {
        List<String> list = Lists.newArrayList();
        list.add(null);
        list.stream().findFirst();
    }
}

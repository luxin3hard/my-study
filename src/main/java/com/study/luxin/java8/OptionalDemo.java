package com.study.luxin.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class OptionalDemo {

    @Test
    /**
     * Optional是返回值不抛出异常,不返回值的一种方案
     */
    public void defaultValue() {
        List<String> list = Lists.newArrayList();
        Optional<String> first = list.stream().findFirst();
        // Optional.of(null)这个会抛出异常,Optional.empty() 中的value=null

        // 如果没有值,返回函数中定义的值
        String str = first.orElse("测试");
        // 通过一个生产者产生的数据
        String orElseGet = first.orElseGet(() -> "fsdfsd");
        // 如果没有值,抛出异常
        first.orElseThrow(NullPointerException::new);
    }

    @Test
    /**
     * 基本类型的优化
     */
    public void optionInt() {
        OptionalInt a = OptionalInt.of(1);
    }


}

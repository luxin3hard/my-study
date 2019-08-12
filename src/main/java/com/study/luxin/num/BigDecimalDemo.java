package com.study.luxin.num;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalDemo {

    @Test
    /**
     * stripTrailingZeros删除无用的0的方法.
     */
    public void removeUnusedZero() {
        BigDecimal v1 = new BigDecimal("1.2340");
        BigDecimal v2 = new BigDecimal("1.234");

        v1 = v1.stripTrailingZeros();
        System.out.println("sdfsd");
        assert v2.equals(v1);
    }


}

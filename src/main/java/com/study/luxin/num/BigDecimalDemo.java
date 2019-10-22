package com.study.luxin.num;

import org.junit.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class BigDecimalDemo {

    @Test
    /**
     * stripTrailingZeros删除无用的0的方法.
     */
    public void removeUnusedZero() {
        BigDecimal v1 = new BigDecimal("264.000");
        BigDecimal v2 = new BigDecimal("1.234");

        v1 = v1.stripTrailingZeros();
        System.out.println("sdfsd");
        assert v2.equals(v1);
    }


    @Test
    /**
     * 处理精度问题,且四舍五入
     */
    public void decimalPlaces() {
        //注意 不要用double初始化 bigDecimal因为double是不准确的,1.5d=1.49999999999,会导致四舍五入不进位
        String numStr = "1234.1235";
        BigDecimal bigDecimal = new BigDecimal(numStr);
        BigDecimal bigDecimal1 = bigDecimal.setScale(3, ROUND_HALF_UP);

        assert bigDecimal1.toString().equals("1234.124");
    }
}

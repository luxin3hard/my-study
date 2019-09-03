package com.study.luxin.str;

import org.junit.Test;

public class CharValueTest {

    @Test
    /**
     * 1的 49对应的int值
     */
    public void charValueTest() {
        String str = "s11.-2";
        for (char c : str.toCharArray()) {
            System.out.println((int) c);
        }


        try {
            Float.parseFloat(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}

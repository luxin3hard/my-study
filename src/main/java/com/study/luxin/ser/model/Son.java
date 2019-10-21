package com.study.luxin.ser.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Son extends Father implements Serializable {

    private int c;
    int d;

    public static int f = 10;

    public Son(int a, int b, int c, int d, int w) {
        super(a, b);
        this.c = c;
        this.d = d;
        f = w;
    }

    public Son() {

    }

}

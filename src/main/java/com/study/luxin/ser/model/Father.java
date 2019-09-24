package com.study.luxin.ser.model;

import lombok.Data;

@Data
public class Father {
    int a;
    int b = 10;

    public Father(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Father() {

    }

}

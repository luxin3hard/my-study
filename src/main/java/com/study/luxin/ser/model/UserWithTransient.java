package com.study.luxin.ser.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserWithTransient implements Serializable {

    private String name;
    private int age;

    private transient Object transientField;
}

package com.study.luxin.ser.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User1HaveNotSerializableField implements Serializable {

    private String name;
    private int age;

    /**
     * 对象 Object 没有Serializable,会抛出NotSerializableException 异常
     */
    private Object notSerializableField;
}

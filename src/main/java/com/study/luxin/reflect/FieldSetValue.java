package com.study.luxin.reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by luxin on 2018/12/18.
 */
public class FieldSetValue {

    @Test
    public void noAccessFieldSetValue() throws NoSuchFieldException, IllegalAccessException {

        ReflectDemo demo = new ReflectDemo("luxin");

        // DeclaredField 可以获取私有的字段
        Field nameField = demo.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(demo, "郝喜珠");

        Object nameValue = nameField.get(demo);
        System.out.println(nameValue);
    }


}

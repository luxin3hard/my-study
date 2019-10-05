package com.study.luxin.effectivejava.o3singloton;

import java.io.Serializable;

// 这种事通过枚举的机制,保证 单例,且序列化也不会生成新的实例,也不怕反射攻击.
// 缺点,枚举 本身不能继承类,只能实现接口
public enum SingletonEnum implements Serializable {
    INSTANCE;
}

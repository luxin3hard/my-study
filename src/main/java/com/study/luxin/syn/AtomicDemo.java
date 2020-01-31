package com.study.luxin.syn;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicDemo {


    @Test
    /**
     * 原子方式更新整个对象
     */
    public void atomicReferenceTest() {
        User user = new User(19, "luxin");
        User newUser = new User(19, "haoxizhu");
        AtomicReference<User> atomicReference = new AtomicReference<>(user);
        User andSet = atomicReference.getAndSet(newUser);

        System.out.println(atomicReference.get());
    }

    @Test
    public void atomicFieldUpdaterTest() {
        AtomicIntegerFieldUpdater<User> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

        User user = new User(19, "luxin");
        System.out.println(fieldUpdater.getAndIncrement(user));
        int i = fieldUpdater.get(user);
        System.out.println("-----" + i);

    }

    private static class User {
        volatile int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


}

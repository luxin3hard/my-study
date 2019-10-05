package com.study.luxin.effectivejava.o20interface;

public abstract class SkeletalAirplane implements Flyable {
    @Override
    public void fly() {
        System.out.println("fly");
    }

    @Override
    public void up() {
        System.out.println("up");
    }


    public abstract void down();
}

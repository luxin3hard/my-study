package com.study.luxin.effectivejava.o20interface;

/**
 * 模拟多继承,可以使用内部类实现抽象类,通过实例转发,实现真正功能. 这样能利用到抽象的已经实现的方法
 */
public class Airplane implements Flyable {

    private Plane plane = new Plane();

    @Override
    public void fly() {
        plane.fly();
    }

    @Override
    public void up() {
        plane.up();
    }

    @Override
    public void down() {
        plane.down();
    }


    static class Plane extends SkeletalAirplane {
        @Override
        public void down() {
            System.out.println("inner class: down");
        }
    }
}

package com.study.luxin.deginpattern.decorator;

/**
 * Created by luxin on 2018/9/3.
 * 装饰者模式
 */
public class Whip extends AbstractCondimentDecorator {

    public Whip(Beverage material) {
        this.material = material;
    }


    @Override
    public double cost() {
        return 0.9 + material.cost();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void setSize(int size) {

    }

}

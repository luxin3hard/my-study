package com.study.luxin.deginpattern.decorator;

/**
 * Created by luxin on 2018/9/3.
 */
public class Mocha extends AbstractCondimentDecorator {

    public Mocha(Beverage material) {
        this.material = material;
    }


    @Override
    public double cost() {
        return 10 + material.cost();
    }

    @Override
    public int getSize() {
        return material.getSize();
    }

    @Override
    public void setSize(int size) {

    }


}

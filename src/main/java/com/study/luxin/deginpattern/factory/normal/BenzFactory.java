package com.study.luxin.deginpattern.factory.normal;

import com.study.luxin.deginpattern.factory.normal.car.Benz;
import com.study.luxin.deginpattern.factory.normal.car.Car;

public class BenzFactory implements CarFactory {
    @Override
    public Car newCar() {
        return new Benz();
    }
}

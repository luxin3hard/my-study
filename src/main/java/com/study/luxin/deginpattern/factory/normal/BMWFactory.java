package com.study.luxin.deginpattern.factory.normal;

import com.study.luxin.deginpattern.factory.normal.car.BMW;
import com.study.luxin.deginpattern.factory.normal.car.Car;

public class BMWFactory implements CarFactory {
    @Override
    public Car newCar() {
        return new BMW();
    }
}

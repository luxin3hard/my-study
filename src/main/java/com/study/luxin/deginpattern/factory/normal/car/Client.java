package com.study.luxin.deginpattern.factory.normal.car;

import com.study.luxin.deginpattern.factory.normal.BenzFactory;
import com.study.luxin.deginpattern.factory.normal.CarFactory;

public class Client {

    public static void main(String[] args) {
        CarFactory carFactory = new BenzFactory();

        Car car = carFactory.newCar();
    }

}

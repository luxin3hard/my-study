package com.study.luxin.deginpattern.factory.abstractfactory;

/**
 * Created by lx on 19/11/2017.
 */
public interface AbstractFactory {
    Engine createEngine();

    AirCondition createAirCondition();
}

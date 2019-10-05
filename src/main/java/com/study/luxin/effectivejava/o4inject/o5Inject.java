package com.study.luxin.effectivejava.o4inject;

import com.study.luxin.deginpattern.strategy.AddStrategy;
import com.study.luxin.deginpattern.strategy.CalculateFactory;
import com.study.luxin.deginpattern.strategy.CalculateStrategy;

public class o5Inject {

    private final CalculateStrategy calculateStrategy;

    /**
     * 这样就是依赖注入
     *
     * @param calculateStrategy
     */
    public o5Inject(CalculateStrategy calculateStrategy) {
        this.calculateStrategy = calculateStrategy;
    }


    // 上面的方式也不够灵活,实际上应该用工厂,或是静态工厂来新建对象
    public o5Inject(CalculateFactory factory, String opt) {
        this.calculateStrategy = factory.createCalculateOpt(opt);
    }


    // 这样灵活性没有了,我们可能有多种操作
    private final CalculateStrategy calculateStrategy01 = new AddStrategy();


}

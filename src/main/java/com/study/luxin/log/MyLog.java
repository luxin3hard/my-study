package com.study.luxin.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * http://logback.qos.ch/manual/configuration.html
 * 官网学习地址
 */
public class MyLog {
    private static Logger log = LoggerFactory.getLogger("com.study.luxin.log.MyLog");

    /**
     * 默认会加载 logback.log logback-test.xml,如果都没有,会默认加载 ConsoleAppender
     */
    @Test
    public void defaultConfig() {
        log.debug("my test!");
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        System.out.println(lc);
    }

    @Test
    /**
     * logback.xml 中的logger 是 myLog的配置
     */
    public void logLevel() {
        ch.qos.logback.classic.Logger myLog = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.foo");
        myLog.setLevel(Level.INFO);
    }


    @Test
    public void mdcUse(){
        MDC.put("traceId","{---trace is mdc---}");

        log.error("hello log");
    }


}

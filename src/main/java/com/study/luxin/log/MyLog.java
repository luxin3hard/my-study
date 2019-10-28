package com.study.luxin.log;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLog {
    private static Logger log = LoggerFactory.getLogger("com.study.luxin.log.MyLog");

    /**
     * 默认会加载 logback.log logback-test.xml,如果都没有,会默认加载 ConsoleAppender
     */
    public void defaultConfig() {
        log.debug("my test!");
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        System.out.println(lc);
    }


}

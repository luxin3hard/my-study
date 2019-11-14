package com.study.luxin.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.MDC;

@Slf4j
public class MDCUseDemo {

    @Test
    /**
     * 结论:能够打印出 trace
     *
     * 11:49:52.239 [main] ERROR c.s.l.l.MDCUseDemo -----luxin----trace----  test log
     */
    public void test() {
        MDC.put("traceId", "-----luxin----trace----");
        log.error("test log");
    }
}

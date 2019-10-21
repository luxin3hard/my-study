package com.study.luxin

import com.google.common.collect.Maps
import com.study.luxin.curl.HttpInvoker
import com.study.luxin.ser.model.Son
import spock.lang.Specification

//如果需要注入spring容器的话
//@ContextConfiguration(value = "classpath:applicationContext.xml")
class GroovyTest extends Specification {

    static {
        // 设置系统变量
        System.setProperty("spring.profiles.active", "fstest")
    }

    def "post_test"() {
        given:
        def url = 'http://10.112.32.47:8006/fs-paas-auth/roleInfo'
        String body = "{\"authContext\":{\"userId\":\"1000\",\"appId\":\"CRM\",\"tenantId\":\"71568\"}}";
        def header = Maps.newHashMap()

        when:
        def post = HttpInvoker.post(url, header, body)
        def response = new String(post)
        println response
        then:
        1 == 1
    }


    def "myTest"() {
        given:
        def son = new Son()
        when:
        // spock 可以直接设置私有的变量
        son.c = 10
        then:
        son.getC() == 10
    }


    def "mock"() {
        given:
        def son = Mock(Son)
        when:
        son.getC() >> 20
        then:
        tr == (son.getC() == va)
        where:
        va || tr
        20 || true
        10 || false
    }


}

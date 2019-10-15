package com.study.luxin

import com.google.common.collect.Maps
import com.study.luxin.curl.HttpInvoker
import spock.lang.Specification

//如果需要注入spring容器的话
//@ContextConfiguration(value = "classpath:applicationContext.xml")
class GroovyTest extends Specification {

    def "my_test"() {
        given:
        String a = ""
        when:
        a = "lu"
        then:
        1 == 1
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


}

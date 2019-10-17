package groovy.com.study.luxin

import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import spock.lang.Specification

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Sputnik.class)
@PrepareForTest([TestClass.class])
class MockStaticMethodSpec extends Specification {


    // 静态方法测试
    def staticString() {
        PowerMockito.mockStatic(TestClass.class)
        Mockito.when(TestClass.staticMethod()).thenReturn("测试用字串")
        return TestClass.staticMethod()
    }


    def "myTest"() {
        when:
        def str = staticString()

        then:
        str == "测试用字串"
    }

    def "测试静态方法"() {
        setup:
        PowerMockito.mockStatic(TestClass.class)

        when:
        Mockito.when(TestClass.staticMethod()).thenReturn("测试用字串")

        then:
        TestClass.staticMethod() == "测试用字串"
    }


    static class TestClass {
        static String staticMethod() {
            return null
        }
    }

}
package groovy.com.study.luxin

import com.study.luxin.code.Code82
import com.study.luxin.code.LinkedNodeUtils
import com.study.luxin.code.ListNode
import spock.lang.Specification

class TestCode82 extends Specification {

    def "deleteDuplicates"() {
        given:
        def code = new Code82()

        ListNode node = LinkedNodeUtils.ofList(nums)
        when:
        def h = LinkedNodeUtils.values2Str(code.deleteDuplicates(node))

        then:
        result == h.equals(t)

        where:
        nums            | t        || result
        [1, 2, 3, 4]    | "1,2,3,4" | true
        [1, 1, 3, 4]    | "3,4"     | true
        [1, 2, 2, 4]    | "1,4"     | true
        [1, 2, 3, 4, 4] | "1,2,3"   | true


    }

}

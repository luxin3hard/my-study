package com.study.luxin.code;

import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/rotate-list/
 * <p>
 * 解题思路:
 * 1. 先找到一共有多少个节点. w
 * 2. 得出i = k%w
 * 3. 倒数第i-最后一个 ,最后一个的指针指向头结点,倒数第i个的上一个节点指向最后一个的next
 */
public class Code61 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int w = 1;
        ListNode h = head;
        ListNode tail = null;

        while (h.next != null) {
            h = h.next;
            tail = h;
            w++;
        }
        int i = k % w;
        if (i == 0) {
            return head;
        } else {
            ListNode tmp = head;
            for (int j = 0; j < w - i - 1; j++) {
                tmp = tmp.next;
            }

            ListNode tmp1 = tmp.next;
            tmp.next = tail.next;
            tail.next = head;
            return tmp1;
        }
    }

    @Test
    public void test() {
        ListNode of = LinkedNodeUtils.of(1, 2, 3, 4, 5);
        ListNode listNode = rotateRight(of, 2);
        System.out.println(LinkedNodeUtils.values2Str(listNode));

    }


}

package com.study.luxin.code;
//https://leetcode-cn.com/problems/sort-list/

import org.junit.Test;

/**
 * 快速排序的思想,每次将head输入,找到改节点的最终位置,然后 将新的head和 节点之后的节点输入,排序
 */


public class Code148 {

    ListNode sortList(ListNode head) {
        ListNode k = new ListNode(-1);
        k.next = head;
        return sortList(k, head, null);
    }


    ListNode sortList(ListNode k, ListNode h, ListNode e) {
        if (h == null) return null;
        if (h == e || h.next == null || h.next == e) return h;
        ListNode w = h;
        ListNode t, k1 = k;
        while (w.next != null && w.next != e) {
            if (w.next.val >= h.val) {
                w = w.next;
            } else {
                t = w.next;
                w.next = t.next;

                k1.next = t;
                k1 = t;
                t.next = h;
            }
        }
        sortList(h, h.next, w.next);
        sortList(k, k.next, h);
        return k.next;
    }

    @Test
    public void test() {
        ListNode head = LinkedNodeUtils.of(1,2,33);
        LinkedNodeUtils.listNodeIterator(head);
        ListNode listNode = sortList(head);
        LinkedNodeUtils.listNodeIterator(listNode);
    }

}

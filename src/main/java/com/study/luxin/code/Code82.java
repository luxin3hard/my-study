package com.study.luxin.code;

import org.junit.Test;

// https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
public class Code82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode w = dummy;
        ListNode f = dummy;

        if (dummy.next == null) {
            return null;
        }

        while (w != null && w.next != null) {
            if ((w.next.next != null && w.next.val != w.next.next.val) || w.next.next == null) {
                w = w.next;
                f.next = w;
                f = w;
            } else {
                ListNode h = w.next.next;
                int value = (int) w.next.val;
                while (h != null && value == (int) h.val) {
                    h = h.next;
                }
                f.next = h;
                f = h;
                w = f;
            }
        }
        return dummy.next;
    }

    @Test
    public void test() {
        ListNode node1 = LinkedNodeUtils.of(1, 2, 2, 3, 4);
        ListNode listNode = deleteDuplicates(node1);

        LinkedNodeUtils.listNodeIterator(listNode);
    }
}

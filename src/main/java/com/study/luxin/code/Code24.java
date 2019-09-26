package com.study.luxin.code;

import org.junit.Test;

public class Code24 {

    /**
     * 注意 2->1 这个1还需要和4连接
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head, q = p.next;
        head = q;

        ListNode t;
        while (true) {
            t = q.next;
            q.next = p;
            p.next = t;

            ListNode w = p;

            p = p.next;
            if (p == null) {
                return head;
            }
            q = p.next;
            if (q == null) {
                return head;
            }
            w.next = q;
        }
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        ListNode listNode = swapPairs(l1);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

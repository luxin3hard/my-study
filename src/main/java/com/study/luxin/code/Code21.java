package com.study.luxin.code;

import org.junit.Test;

//https://leetcode-cn.com/problems/merge-two-sorted-lists/
public class Code21 {
    public ListNode mergeTwoLists(ListNode p, ListNode q) {
        ListNode t = new ListNode(-1);
        ListNode head = t;

        while (p != null && q != null) {
            if (p.val <= q.val) {
                t.next = p;
                t = t.next;
                p = p.next;
            } else {
                t.next = q;
                t = t.next;
                q = q.next;
            }
        }
        if (p != null) {
            t.next = p;
        }
        if (q != null) {
            t.next = q;
        }
        return head.next;
    }


    @Test
    public void test() {

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);

        ListNode l3 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;


        ListNode c1 = new ListNode(1);
        ListNode c2 = new ListNode(3);

        ListNode c3 = new ListNode(4);
        c1.next = c2;
        c2.next = c3;

        ListNode listNode = mergeTwoLists(l1, c1);
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

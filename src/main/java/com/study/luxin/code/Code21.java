package com.study.luxin.code;

import org.junit.Test;

//https://leetcode-cn.com/problems/merge-two-sorted-lists/
public class Code21 {
    public ListNode mergeTwoLists(ListNode p, ListNode q) {
        if (p == null) {
            return q;
        }
        if (q == null) {
            return p;
        }
        ListNode head = p.val <= q.val ? p : q;
        ListNode last;
        ListNode t;
        while (true) {
            if (p.val <= q.val) {
                last = p;
                p = p.next;
            } else {
                t= p.next;
                p.next = q;
                last = q;
                q = q.next;
                p.next=t;
            }

            if (p == null) {
                last.next = q;
                return head;
            }

            if (q == null) {
                last.next = p;
                return head;
            }
        }
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

package com.study.luxin.code;

import org.junit.Test;
import org.springframework.cglib.core.Local;

public class RevertLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    // 关键在于定位 head 和原来的head节点,原来的head需要一直被标记
    public ListNode revert(ListNode head) {
        ListNode p = head;
        ListNode tmpHead;
        while (p.next != null) {
            tmpHead = head;

            head = p.next;
            p.next = head.next;
            head.next = tmpHead;

        }
        return head;
    }


    public void ergodicLinkedList(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }


    @Test
    public void test() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);

        n1.next = n2;
        n2.next = n3;
        ergodicLinkedList(revert(n1));
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean up = false;
        boolean f = true;
        ListNode t = null;

        ListNode head = null;

        while (l1 != null || l2 != null || up) {
            if (f) {
                int value = l1.val + l2.val;

                if (value >= 10) {
                    up = true;
                    value = value - 10;
                }
                head = new ListNode(value);

                t = head;
                l1 = l1.next;
                l2 = l2.next;
                f = false;
            } else {

                int value;
                if (up) {
                    value = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + 1;
                } else {
                    value = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
                }
                if (value >= 10) {
                    value = value - 10;
                    up = true;
                } else {
                    up = false;
                }


                t.next = new ListNode(value);
                t = t.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
        }

        return head;
    }


    public ListNode addTwoNumbers01(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode t = head;
        boolean up = false;

        while (l1 != null || l2 != null || up) {
            int value = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);

            if (up) {
                value = value + 1;
            }

            if (value >= 10) {
                value = value - 10;
                up = true;
            } else {
                up = false;
            }

            t.next = new ListNode(value);
            t = t.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return head.next;
    }


    @Test
    public void test1() {
        ListNode b1 = new ListNode(2);
        ListNode b2 = new ListNode(4);
        ListNode b3 = new ListNode(3);
        b1.next = b2;
        b2.next = b3;

        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(6);
        ListNode n3 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        ergodicLinkedList(addTwoNumbers01(b1, n1));
    }


}

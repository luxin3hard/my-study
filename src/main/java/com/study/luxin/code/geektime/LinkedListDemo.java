package com.study.luxin.code.geektime;

import com.google.common.collect.Lists;
import com.study.luxin.code.LinkedNodeUtils;
import com.study.luxin.code.ListNode;
import org.junit.Test;

public class LinkedListDemo {

    /**
     * 我的思路是 翻转法然后 在用String的equal比较
     * <p>
     * 实际应该用 两个指针,一个每次走1不,另一个一次走两步,获得中间节点. 然后 从中间节点后的节点翻转. 然后 新的链表比较.
     *
     * @param head
     * @return
     */
    public ListNode noonString(ListNode head) {
        ListNode h = head;
        ListNode n = head;

        if (h == null) {
            return null;
        }

        while (n.next != null) {
            ListNode t = n.next.next;

            n.next.next = h;
            h = n.next;
            n.next = t;
        }
        return h;
    }


    @Test
    public void test() {
        ListNode node = LinkedNodeUtils.ofList(Lists.newArrayList('a', 'b', 'c', 'd'));
        //System.out.println(LinkedNodeUtils.values2Str(noonString(node)));

        ListNode<Integer> n1 = new ListNode(1);
        ListNode<Integer> n2 = new ListNode(1);
        ListNode<Integer> n3 = new ListNode(1);
        ListNode<Integer> n4 = new ListNode(1);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;


        System.out.println(isCycle(n1));

    }

    /**
     * 判断一个链表是否有环
     */
    public boolean isCycle(ListNode<Integer> head) {
        if (head == null) {
            return false;
        }

        ListNode<Integer> m = head, n = head;
        while (true) {
            m = m.next;
            if (m == null) {
                return false;
            }

            n = n.next.next;
            if (n == null) {
                return false;
            }

            if (n == m) {
                return true;
            }
        }
    }


    /**
     * 有序列表合并
     *
     * @param n
     * @param m
     * @return
     */
    public ListNode mergeTwoSortLinkedList(ListNode<Integer> n, ListNode<Integer> m) {
        if (n == null) {
            return m;
        }
        if (m == null) {
            return n;
        }
        ListNode<Integer> h = null, p = null;
        while (n != null && m != null) {
            if (n.val < m.val) {
                if (h == null) {
                    h = n;
                    n = n.next;
                    p = h;
                } else {
                    p.next = n;
                    p = n;
                    n = n.next;
                }
            } else {
                if (h == null) {
                    h = m;
                    m = m.next;
                    p = h;

                } else {
                    p.next = m;
                    p = m;
                    m = m.next;
                }
            }
        }

        if (n != null) {
            p.next = n;
        }
        if (m != null) {
            p.next = m;
        }
        return h;
    }


    @Test
    public void mergeTest() {
        ListNode n1 = LinkedNodeUtils.of(1, 3, 7);
        ListNode n2 = LinkedNodeUtils.of(2, 4, 5);
        ListNode node = mergeTwoSortLinkedList(n1, n2);
        System.out.println(LinkedNodeUtils.values2Str(node));
    }


}

package com.study.luxin.code;

import java.util.List;

public class LinkedNodeUtils {

    public static ListNode of(int... nums) {
        ListNode t = null;

        ListNode head = null;
        for (int num : nums) {
            if (t == null) {
                t = new ListNode(num);
                head = t;
            } else {
                t.next = new ListNode(num);
                t = t.next;
            }
        }
        return head;
    }


    public static ListNode ofList(List<Integer> nums) {
        ListNode t = null;

        ListNode head = null;
        for (int num : nums) {
            if (t == null) {
                t = new ListNode(num);
                head = t;
            } else {
                t.next = new ListNode(num);
                t = t.next;
            }
        }
        return head;
    }


    public static void listNodeIterator(ListNode head) {
        ListNode w = head;
        String str = "";
        if (w == null) {
            return;
        }
        while (w != null) {
            str = str + w.val + ",";
            w = w.next;
        }
        System.out.println(str.substring(0, str.length() - 1));
    }


    public static String values2Str(ListNode head) {
        ListNode w = head;
        String str = "";
        if (w == null) {
            return "";
        }

        String s = "";
        while (w != null) {
            str = str + w.val + ",";
            w = w.next;
        }
        return str.substring(0, str.length() - 1);
    }


}

package com.study.luxin.code.geektime.seven;

import org.junit.Test;

public class LinkedList {

    @Test
    public void singleLinkedListTest() {
        MyLinkedList<Integer> linkedList = new MyLinkedList();

        linkedList.addLast(1);
        linkedList.addLast(2);
        System.out.println(linkedList);
        linkedList.remove(1);
        System.out.println(linkedList);
        linkedList.addLast(2);
        System.out.println(linkedList);
    }

    @Test
    public void revertSingleLinkedListTest() {
        MyLinkedList<Integer> linkedList = new MyLinkedList();

        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.revert();
        System.out.println(linkedList);
    }

    static class Node<T> {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * 单链表
     */
    public static class MyLinkedList<T> {

        Node head = new Node(null);
        Node tail;
        Node prvTail;

        public void addLast(T value) {
            if (head.next == null && tail == null) {
                head.next = new Node(value);
                tail = head.next;
            } else {
                tail.next = new Node(value);
                prvTail = tail;
                tail = tail.next;
            }
        }


        public void remove(T value) {
            Node t = head;
            while (t.next != null) {

                if (t.next.value == value) {
                    t.next = t.next.next;
                    break;
                }
            }
        }


        public void revert() {
            if (head.next == null) {
                return;
            }

            Node t = head.next;

            while (t.next != null) {
                Node w = t.next;
                t.next = w.next;
                w.next = head.next;
                head.next = w;
            }
        }


        @Override
        public String toString() {
            String str = "";
            Node t = head;
            while (t.next != null) {
                str = str + " " + t.next.value.toString();
                t = t.next;
            }
            return str;
        }
    }


    public static class CircleLinkedList<T> {
        Node head;
        Node tail;

        public void add(T value) {
            if (head == null && tail == null) {
                head = new Node(value);
            } else if (tail == null) {
                tail = new Node(value);
                tail.next = head;
            } else {
                Node tmp = new Node(value);
                tail.next = tmp;
                tail = tail.next;
                tail.next = head;
            }
        }
    }







}

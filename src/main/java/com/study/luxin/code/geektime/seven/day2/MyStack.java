package com.study.luxin.code.geektime.seven.day2;

import org.junit.Test;

public class MyStack {

    @Test
    public void test0() {
        OrderStack stack = new OrderStack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    @Test
    public void test1() {
        LinkedStack stack = new LinkedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


    @Test
    public void test2() {
        Chrome chrome = new Chrome();

        chrome.newPage(1);
        chrome.newPage(2);
        chrome.newPage(3);

        System.out.println(chrome.back());
        System.out.println(chrome.forward());
        System.out.println(chrome.back());
        chrome.newPage(4);

        System.out.println(chrome.forward());
    }

    public static class OrderStack {
        private int[] ar;
        private int pos = -1;
        private int size;


        public OrderStack(int size) {
            this.size = size;
            ar = new int[size];
        }


        public void push(int value) {
            if (pos < size - 1) {
                ar[++pos] = value;
            } else {
                throw new RuntimeException("full");
            }
        }

        public int pop() {
            if (pos >= 0) {
                int re = ar[pos];
                ar[pos--] = 0;
                return re;
            } else {
                throw new RuntimeException("empty");
            }
        }
    }


    public static class LinkedStack {

        Node head = new Node(-1);

        public void push(int v) {
            Node temp = head.next;

            if (temp == null) {
                head.next = new Node(v);
            } else {
                head.next = new Node(v);
                head.next.next = temp;
            }
        }

        public int pop() {
            Node temp = head.next;

            if (temp == null) {
                throw new RuntimeException("empty");
            } else {
                head.next = head.next.next;
                return temp.value;
            }
        }

        public int take() {
            Node temp = head.next;

            if (temp == null) {
                throw new RuntimeException("empty");
            } else {
                return temp.value;
            }
        }

        public void clean() {
            head.next = null;
        }


        class Node {
            Node next;
            int value;

            public Node(int va) {
                this.value = va;
            }
        }
    }


    public static class Chrome {
        LinkedStack newPage;
        LinkedStack oldPage;

        public Chrome() {
            newPage = new LinkedStack();
            oldPage = new LinkedStack();
        }


        public int forward() {
            int temp = oldPage.pop();

            newPage.push(temp);
            return temp;
        }

        public int back() {
            int pop = newPage.pop();
            oldPage.push(pop);
            return newPage.take();
        }

        public void newPage(int n) {
            oldPage.clean();
            newPage.push(n);
        }
    }


}

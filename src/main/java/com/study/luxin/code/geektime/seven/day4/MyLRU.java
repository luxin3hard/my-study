package com.study.luxin.code.geektime.seven.day4;

import com.google.common.collect.Maps;

import java.util.Map;

public class MyLRU<T> {

    private Map<T, Node> values;
    private int size;
    private int capacity;
    private Node head;
    private Node tail;

    public MyLRU(int size) {
        this.size = size;
        values = Maps.newHashMapWithExpectedSize(size);
        capacity = 0;
        // head指向一个dummy节点
        head = new Node(null);
    }

    public static void main(String[] args) {
        MyLRU<Object> lru = new MyLRU<>(3);

        lru.add(1);
        lru.add(2);
        lru.add(3);

        lru.print();

        System.out.println("---------------------\n");
        lru.add(1);
        lru.print();


    }

    public void add(T value) {
        Node<T> node = values.get(value);

        // 数据已经存在,移动node将node放入队尾
        if (node != null) {
            if (tail != node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;

                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            return;
        }


        node = new Node<>(value);
        if (capacity < size) {// 没有满,加在最后
            capacity++;

            if (capacity == 1) {
                tail = node;
                head.next = tail;
                tail.prev = head;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }

        } else { //已经满了,将头去掉,node变成尾部
            Node t = head.next;
            head.next = t.next;
            t.next.prev = head;

            tail.next = node;
            node.prev = tail;
            tail = node;

            values.remove(t.value);
            values.put(node.value, node);
        }
    }

    public void print() {
        Node next = head.next;
        while (next != null) {
            System.out.println(next.value);
            next = next.next;
        }
    }

    private static class Node<T> {
        private Node next;
        private Node prev;
        private T value;

        public Node(T value) {
            this.value = value;
        }
    }


}

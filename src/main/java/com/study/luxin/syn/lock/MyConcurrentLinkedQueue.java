package com.study.luxin.syn.lock;

import sun.misc.Unsafe;

public class MyConcurrentLinkedQueue<T> {

    volatile Node head;
    volatile Node tail;

    private class Node<T> {
        Node next;
        T val;

        Node(T val) {
            this.val = val;
        }
    }


    void offer(T val) {
        Node node = new Node(val);


        if(head == null){

            //Unsafe.getUnsafe().compareAndSwapObject()

        }


    }


}

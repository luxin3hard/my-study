package com.study.luxin.code.geektime;

import java.util.LinkedList;

public class Graph {
    private int v;
    private LinkedList[] tm = null;

    public Graph(int v) {
        this.v = v;

        for (int i = 0; i < v; i++) {
            tm[i] = new LinkedList();
        }
    }

    public void addEdge(int s, int t) {
        tm[s].add(t);
    }
}

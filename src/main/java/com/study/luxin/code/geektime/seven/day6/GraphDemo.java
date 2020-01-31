package com.study.luxin.code.geektime.seven.day6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphDemo {

    public static class NoDirectionGraph {
        private int nodeCount;
        private LinkedList<Integer> ajs[];

        public NoDirectionGraph(int nodeCount) {
            this.nodeCount = nodeCount;
            ajs = new LinkedList[nodeCount];
            for (int i = 0; i < nodeCount; i++) {
                ajs[i] = new LinkedList<>();
            }
        }

        public void addEdge(int i,int j){
            ajs[i].add(j);
            ajs[j].add(i);
        }
    }


    public void breadFirstSearch(NoDirectionGraph graph){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited=new boolean[graph.nodeCount];
        int[] prev = new int[graph.nodeCount];







    }













}

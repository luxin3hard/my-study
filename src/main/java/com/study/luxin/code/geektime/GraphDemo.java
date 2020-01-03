package com.study.luxin.code.geektime;

import java.util.LinkedList;
import java.util.Queue;

public class GraphDemo {
    /**
     * breadth first search
     * <p>
     * bfs广度优先搜索算法
     */

    public void bfs(Graph graph, int st, int en) {

        int v = graph.getV();
        LinkedList<Integer>[] tm = graph.getTm();

        boolean[] visited = new boolean[v];

        /**
         * 记录节点的前置节点是哪个
         * 比如prev[4]=3 标识4节点的前置节点是3
         */

        int[] prev = new int[v];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(st);

        while (!queue.isEmpty()) {
            int t = queue.poll();
            for (int o : tm[t]) {

                if (!visited[o]) {

                    visited[o] = true;
                    if (o == t) {
                        print(prev, st, en);
                        return;
                    } else {
                        queue.add(o);
                        prev[o] = t;
                    }
                }
            }
        }
    }

    private void print(int[] prev, int st, int en) {

        if (en != st) {
            print(prev, st, prev[en]);
            System.out.println(en);
        }

    }






}

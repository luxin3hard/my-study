package com.study.luxin.code.geektime;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 拓扑排序
 */
public class TuoPuSort {

    public void kahnSort(Graph graph) {
        int v = graph.getV();
        LinkedList<Integer>[] tm = graph.getTm();

        int[] du = new int[v];
        for (int i = 0; i < v; i++) {
            LinkedList<Integer> t = tm[i];
            for (int o : t) {
                du[o]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (du[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int t = queue.poll();
            for (Integer w : tm[t]) {
                du[w]--;
                if (du[w] == 0) {
                    queue.add(w);
                }
            }
        }
    }


    /**
     * 深度优先搜索实现拓扑排序
     */
    public void dfsTuoPuSort(Graph graph) {

        int v = graph.getV();
        LinkedList<Integer>[] tm = graph.getTm();
        boolean[] visited = new boolean[v];


        // 先将邻接表变成逆邻接表
        // 之前 s依赖t,写作 t->s
        // 逆邻接表 s依赖t,用 s->t表示
        LinkedList<Integer>[] revert = new LinkedList[v];
        for (int i = 0; i < tm.length; i++) {
            for (Integer w : tm[i]) {
                revert[w].add(i);
            }
        }

        for (int i = 0; i < v; i++) {

            dfs(i, visited, revert);
        }
    }

    private void dfs(int i, boolean[] visited, LinkedList<Integer>[] revert) {

        if (!visited[i]) {
            for (Integer w : revert[i]) {
                dfs(w, visited, revert);
            }
            System.out.println(i);
        }


    }


}

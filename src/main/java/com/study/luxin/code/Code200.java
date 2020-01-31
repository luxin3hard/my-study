package com.study.luxin.code;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Code200 {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        while (true) {
            int[] notVisitLandPosition = findNotVisitLandPosition(visited, grid);
            if (notVisitLandPosition[0] == -1) {
                return count;
            } else {
                count++;
                deathFirstSearch(visited, notVisitLandPosition, grid);
            }
        }
    }

    private void deathFirstSearch(boolean[][] visited, int[] notVisitLandPosition, char[][] grid) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(notVisitLandPosition);

        while (!queue.isEmpty()) {

            int[] poll = queue.poll();
            int i = poll[0];
            int j = poll[1];
            visited[poll[0]][poll[1]] = true;

            if (i - 1 >= 0 && !visited[i - 1][j] && grid[i - 1][j] == '1') {
                queue.add(new int[]{i - 1, j});
                visited[i - 1][j] = true;
            }

            if (i + 1 < grid.length && !visited[i + 1][j] && grid[i + 1][j] == '1') {
                queue.add(new int[]{i + 1, j});
                visited[i + 1][j] = true;
            }


            if (j - 1 >= 0 && !visited[i][j - 1] && grid[i][j - 1] == '1') {
                queue.add(new int[]{i, j - 1});
                visited[i][j - 1] = true;
            }

            if (j + 1 < grid[0].length && !visited[i][j + 1] && grid[i][j + 1] == '1') {
                queue.add(new int[]{i, j + 1});
                visited[i][j + 1] = true;
            }
        }


    }

    private int[] findNotVisitLandPosition(boolean[][] visited, char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '1' && !visited[i][j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }


    @Test
    public void test() {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        System.out.println(numIslands(grid));
    }

}

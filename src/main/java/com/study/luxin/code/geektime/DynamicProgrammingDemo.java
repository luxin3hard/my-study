package com.study.luxin.code.geektime;

import org.junit.Test;

import static java.lang.Math.min;

public class DynamicProgrammingDemo {

    public int oneZeroPackage(int[] items, int[] value, int max) {
        int[] tmp = new int[max + 1];
        for (int i = 0; i < max; i++) {
            tmp[i] = -1;
        }

        tmp[0] = 0;
        int m = Integer.MIN_VALUE;
        for (int i = 0; i < items.length; i++) {
            for (int j = max - items[i]; j >= 0; j--) {
                if (tmp[j] != -1) {
                    if (j + items[i] <= max) {
                        tmp[j + items[i]] = tmp[j] + value[i];
                        if (tmp[j + items[i]] > m) {
                            m = tmp[j + items[i]];
                        }
                    }

                }
            }
        }
        return m;
    }


    @Test
    public void oneZeroPackageTest() {
        int[] items = {2, 2, 4, 6, 3};
        int[] value = {3, 6, 8, 9, 6};
        int i = oneZeroPackage(items, value, 9);
        System.out.println(i);
    }


    /**
     * 矩阵最短路径
     * <p>
     * f(i,j)=min((f(i-1,j)+m[i-1,j]+m[i,j]),(f(i,j-1)+m[i,j-1]+m[i,j])))
     */

    private int minPath(int[][] m) {
        int h = m.length;
        int l = m[0].length;

        int[][] f = new int[h][l];


        for (int i1 = 0; i1 < m.length; i1++) {
            f[0][i1] = +m[0][i1];
        }
        for (int i1 = 0; i1 < m[0].length; i1++) {
            f[i1][0] = +m[i1][0];
        }

        int i = 1, j = 1;
        while (i < h || j < l) {
            for (int k = i; k < l; k++) {
                f[i][k] = min((f[i - 1][k] + m[i][k]), (f[i][k - 1] + m[i][k]));
            }

            for (int k = j + 1; k < h; k++) {
                f[k][j] = min((f[k - 1][j] + m[k][j]), (f[k][j - 1] + m[k][j]));
            }

            i++;
            j++;
        }
        return f[h - 1][l - 1];
    }


    @Test
    public void minPathTest() {

        int[][] m = new int[][]{
                {0, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}};

        System.out.println(minPath(m));
    }


}

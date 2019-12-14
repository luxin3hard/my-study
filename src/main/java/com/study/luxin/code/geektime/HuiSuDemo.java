package com.study.luxin.code.geektime;

import java.util.Arrays;

public class HuiSuDemo {

    public void eightQueen() {
        int[] n = new int[8];
        setQueen(n, 0);

    }

    private void setQueen(int[] n, int row) {
        if (row == 8) {
            System.out.println(Arrays.toString(n));
            return;
        }

        // 当前行的棋子可能放置在8列中任意一列
        // 每一种放置方法都要尝试
        for (int l = 0; l < 8; l++) {
            if (isOk(row, l)) {
                n[row] = l;
                setQueen(n, row + 1);
            }
        }

    }

    private boolean isOk(int row, int l) {
        return false;
    }


    /**
     * 0-1背包问题
     * <p>
     * items 重量 价值
     */
    public void zeroOnePackage(int[][] items, int wight, int size) {
        int max = Integer.MIN_VALUE;
        setPackage(items, wight, max, 0, 0, size);
    }

    private void setPackage(int[][] items, int w, int wight, int max, int i, int size) {

        if (i > size || w == wight) {
            return;
        }


        if (w + items[i][0] <= wight) {
            setPackage(items, w + items[i][0], wight, max, i + 1, size);
        }

        setPackage(items, w, wight, max, i + 1, size);


    }


}

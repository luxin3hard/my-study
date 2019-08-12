package com.study.luxin.code.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by lx on 08/07/2018.
 * https://www.cnblogs.com/chengxiao/p/6194356.html
 */
public class MergeSort {


    public void merge(int[] array, int l, int m, int r, int[] tmp) {

        int k = m + 1;
        int p = 0;

        while (l <= m && k <= r) {
            if (array[l] < array[k]) {
                tmp[p++] = array[l++];
            } else {
                tmp[p++] = array[k++];
            }
        }

        while (l <= m) {
            tmp[p++] = array[l++];
        }


        while (k <= r) {
            tmp[p++] = array[k++];
        }
    }


    /**
     * 分治模式:在每一层递归分为三个步骤:
     * 1.分解:将原来的问题分解成子问题
     * 2.解决:递归将各个子问题解决,当啊子问题足够小,直接解决.
     * 3.合并:将子问题的结果合并成原问题的解
     * <p>
     * 合并排序: 当分解成1个元素的时候,自然是有序的.
     */

    public void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }


    public void mergeSort(int[] array, int b, int e) {

        int mid;
        if (b != e) {
            mid = (b + e) / 2;
        } else {
            return;
        }


        mergeSort(array, b, mid);
        mergeSort(array, mid + 1, e);
        mergeArray(array, b, mid, e, new int[array.length]);
    }

    private void mergeArray(int[] array, int b, int mid, int e, int[] temp) {
        int i = b, j = mid + 1;
        int p = 0;

        while (i <= mid && j <= e) {
            if (array[i] <= array[j]) {
                temp[p++] = array[i++];
            } else {
                temp[p++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[p++] = array[i++];
        }
        while (j <= e) {
            temp[p++] = array[j++];
        }

        int w = 0;
        for (int k = b; k <= e; k++) {
            array[k] = temp[w++];
        }
    }


    @Test
    public void mergeSortTest() {

        int[] array = new int[]{1,42,4, 3};
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }


}

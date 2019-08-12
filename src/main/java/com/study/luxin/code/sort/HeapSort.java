package com.study.luxin.code.sort;

import org.junit.Test;

import java.util.Arrays;

public class HeapSort {


    /**
     * 假设,左右子树都是 大顶堆,新增一个节点
     */
    void maxHeapify(int[] array, int i, int size) {
        int maxIndex = i;

        int left = i * 2 + 1;
        if (left < size && array[i] < array[left]) {
            maxIndex = left;
        }


        int right = (i + 1) * 2;
        if (right < size && array[maxIndex] < array[right]) {
            maxIndex = right;
        }

        if (maxIndex != i) {
            int tmp = array[i];
            array[i] = array[maxIndex];
            array[maxIndex] = tmp;
            maxHeapify(array, maxIndex, size);
        }
    }

    /**
     * 建立最大堆,现将树的子树变成大顶堆,然后在将子树的父节点构成的树变成大顶堆.
     * 最下面的子树的根节点是size / 2 - 1, 比如有10个元素那么第五个元素是根节点,那么他的下标为10/2-1=4
     *
     * @param array
     */
    void buildMaxHeap(int[] array) {

        int size = array.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            maxHeapify(array, i, size);
        }
    }


    void heapSort(int[] array) {
        buildMaxHeap(array);

        for (int i = array.length - 1; i >= 1; i--) {
            exchange(array, 0, i);
            maxHeapify(array, 0, i);
        }
    }

    private void exchange(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    @Test
    public void test() {
        int[] array = new int[]{16, 14, 10, 8, 7, 9, 3, 2, 4, 1};

        heapSort(array);
        System.out.println(Arrays.toString(array));
    }


}

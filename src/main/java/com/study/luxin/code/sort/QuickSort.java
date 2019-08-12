package com.study.luxin.code.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {


    void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int s, int e) {
        if (s < e) {
            int p = partition02(array, s, e);
            quickSort(array, s, p - 1);
            quickSort(array, p + 1, e);
        }
    }

    private int partition(int[] array, int s, int e) {
        int needPositionValue = array[s];
        int i = s, j = e;

        int p = s;
        while (i < j) {
            if (i < j) {
                while (i < j && array[j] >= needPositionValue) j--;
                array[p] = array[j];
                p = j--;
            }

            if (i < j) {
                while (i < j && array[i] <= needPositionValue) i++;
                array[p] = array[i];
                p = i++;
            }
        }
        array[i] = needPositionValue;
        return i;
    }


    private int partition02(int[] array, int s, int e) {
        int v = array[s];
        int i = s - 1, j = e + 1;

        while (true) {
            do{
                j--;
            } while (array[j] >=v);

            do {
                i++;
            } while (array[i] <=v);


            if (i < j) {
                exchange(array, i, j);
            } else {
                return j;
            }
        }
    }


    private int partition01(int[] array, int s, int e) {
        int i = s - 1;

        int value = array[e];

        for (int j = s; j < e - 1; j++) {
            if (array[j] <= value) {
                i++;
                exchange(array, i, j);
            }
        }

        exchange(array, i + 1, e);
        return i + 1;
    }

    private void exchange(int[] array, int i, int j) {

        if (i != j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }


    @Test
    public void test() {
        int[] array = new int[]{2, 1, 4, 6, 0, 7, 9, -1};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }


}

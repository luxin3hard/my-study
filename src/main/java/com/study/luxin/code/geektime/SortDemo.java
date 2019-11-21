package com.study.luxin.code.geektime;

import org.junit.Test;

import java.util.Arrays;

public class SortDemo {

    /**
     * 如果有n个数,最多需要循环n次
     *
     * @param nums
     */
    public void mpSort(int[] nums) {
        boolean changed = false;
        int t;

        for (int r = nums.length; r > 0; r--) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    t = nums[i + 1];
                    nums[i + 1] = nums[i];
                    nums[i] = t;
                    changed = true;
                }
            }
            if (!changed) {
                break;
            }
        }
    }

    @Test
    public void mpTest() {
        int[] nums = new int[]{1, 3, 2, 5, 0};

        mpSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 插入排序的要点是: t>=1 防止下标越界. 和前一个值比较,如果比前一个小,那么前一个数向后移动
     *
     * @param n
     */
    public void insertSort(int[] n) {
        for (int i = 1; i < n.length; i++) {
            int t = i;
            int k = n[t];
            while (t >= 1 && n[t - 1] > k) {
                n[t] = n[t - 1];
                t--;
            }
            n[t] = k;
        }
    }

    @Test
    public void insertSortTest() {
        int[] nums = new int[]{1, 3, 2, 5, 0};

        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 思想: 找到最小的放在第一个位置,将原第一个数和最小的位置的数交换. 然后从剩余的找到最小的,放在第二个位置...
     *
     * @param n
     */
    public void selectSort(int[] n) {
        for (int i = 0; i < n.length; i++) {
            int min = n[i];
            int p = i;
            for (int j = i + 1; j < n.length; j++) {
                if (min > n[j]) {
                    min = n[j];
                    p = j;
                }
            }
            n[p] = n[i];
            n[i] = min;
        }
    }

    @Test
    public void selectSortTest() {
        int[] nums = new int[]{1, 3, 2, 5, 0};

        selectSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public void mergeSort(int[] n) {
        mergeSort(n, 0, n.length - 1, new int[n.length]);
    }

    private void mergeSort(int[] n, int l, int h, int[] tmp) {
        int mid = (l + h) / 2;

        if (l < h) {
            mergeSort(n, l, mid, tmp);
            mergeSort(n, mid + 1, h, tmp);
            merge(n, l, h, mid, tmp);
        }
    }

    private void merge(int[] n, int l, int h, int mid, int[] tmp) {
        int l1 = l;
        int h1 = mid + 1;
        int k = 0;

        while (l1 <= mid && h1 <= h) {
            if (n[l1] <= n[h1]) {
                tmp[k++] = n[l1++];
            } else {
                tmp[k++] = n[h1++];
            }
        }

        while (l1 <= mid) {
            tmp[k++] = n[l1++];
        }

        while (h1 <= h) {
            tmp[k++] = n[h1++];
        }


        int w = 0;
        for (int i = l; i <= h; i++) {
            n[i] = tmp[w++];
        }
    }

    @Test
    public void mergeSortTest() {
        int[] nums = new int[]{1, 3, 2, 5, 0};

        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 桶排序,一般用作外部排序. 数据量大,但是内存小.
     * 先扫描出数据范围,然后根据数据范围m,将m分成n个桶.
     * 然后扫描数据,将数据加入到对应的桶内.
     * 分别加载桶做快速排序
     */

}

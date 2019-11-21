package com.study.luxin.code.geektime;

import org.junit.Test;

import java.util.Arrays;

public class QuickSortDemo {


    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int b, int e) {
        int t = nums[b];
        int p = b;
        int i = b, j = e;
        if (e > b) {
            while (i < j) {
                while (j > i && nums[j] >= t) {
                    j--;
                }

                if (i < j) {
                    nums[p] = nums[j];
                    p = j;
                }


                while (j > i && nums[i] <= t) {
                    i++;
                }

                if (i < j) {
                    nums[p] = nums[i];
                    p = i;
                }
            }
            nums[i] = t;
            quickSort(nums, b, i - 1);
            quickSort(nums, i + 1, e);
        }
    }


    @Test
    public void quickSortTest() {
        int[] nums = new int[]{1, 0, 4, 7, 8, 5};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }


}

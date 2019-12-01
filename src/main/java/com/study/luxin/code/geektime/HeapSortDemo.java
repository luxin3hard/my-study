package com.study.luxin.code.geektime;

import org.junit.Test;

import java.util.Arrays;

public class HeapSortDemo {


    /**
     * 生成大顶堆,使用往堆里面插入数据. 有两种方式,从上往下.
     */
    // 这种事自上向下的方式
    void insertBuildHeap(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            adaptHeap(nums, i);
        }
    }

    private void adaptHeap(int[] nums, int i) {
        int t = i;
        int w;
        while (t > 1) {
            w = t / 2;
            if (nums[t] > nums[w]) {
                int q = nums[t];
                nums[t] = nums[w];
                nums[w] = q;
            }
            t = w;
        }
    }


    void heapSort(int[] nums) {
        // 先将堆调整为大顶堆
        insertBuildHeap(nums);

        deleteAdaptHeap(nums);


    }

    private void deleteAdaptHeap(int[] nums) {

        int s = nums.length - 1;

        while (s >= 2) {
            int t = nums[s];
            nums[s] = nums[1];
            nums[1] = t;
            deleteAdapt(nums, --s);
        }
    }

    private void deleteAdapt(int[] nums, int i) {
        int t = 2;
        int m;

        while (t <= i) {
            if (t + 1 <= i) {
                if (nums[t + 1] > nums[t]) {
                    m = t + 1;
                } else {
                    m = t;
                }
            } else {
                m = t;
            }

            if (nums[m] > nums[m / 2]) {
                int w = nums[m / 2];
                nums[m / 2] = nums[m];
                nums[m] = w;

                t = m * 2;
            } else {
                break;
            }
        }
    }


    @Test
    public void test() {
        int[] nums = new int[]{-1, 1, 2, 5, 4, 3};

        heapSort(nums);

        System.out.println(Arrays.toString(nums));
    }


}

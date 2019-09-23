package com.study.luxin.code;

import org.junit.Test;

import java.util.Arrays;

//https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class Code34 {

    public int[] searchRange(int[] nums, int target) {
        int[] a = new int[]{-1, -1};
        searchPosition(nums, 0, nums.length, target, a);
        return a;
    }


    public int searchPosition(int[] nums, int l, int h, int target, int[] a) {
        int m = (l + h) / 2;

        if (l <= h && m >= 0 && m < nums.length) {
            if (nums[m] == target) {
                int t1 = searchPosition(nums, l, m - 1, target, a);
                int t2 = searchPosition(nums, m + 1, h, target, a);

                if (t1 == -1) {
                    a[0] = a[0] == -1 ? m : Math.min(m, a[0]);
                } else {
                    a[0] = a[0] == -1 ? t1 : Math.min(t1, a[0]);
                }

                a[1] = Math.max(Math.max(a[1],t2),m);
                return m;
            }
            if (nums[m] > target) {
                searchPosition(nums, l, m - 1, target, a);
            } else {
                searchPosition(nums, m + 1, h, target, a);
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1,2,3,3,3,3,4,5,9};
        System.out.println(Arrays.toString(searchRange(nums, 3)));
    }

}

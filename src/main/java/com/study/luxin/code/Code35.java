package com.study.luxin.code;

import org.junit.Test;

//https://leetcode-cn.com/problems/search-insert-position/
public class Code35 {

    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int l = 0, h = nums.length;

        int m;
        while (l <= h && (m = (l + h) / 2) < nums.length) {
            int t = nums[m];
            if (t == target) {
                return m;
            }
            if (t > target) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }


    @Test
    public void test() {
        int[] nums = new int[]{1, 3, 4, 7};
        System.out.println(searchInsert(nums, 0));
        System.out.println(searchInsert(nums, 2));
        System.out.println(searchInsert(nums, 8));
        System.out.println(searchInsert(nums, 5));
        System.out.println(searchInsert(nums, 4));
    }


}

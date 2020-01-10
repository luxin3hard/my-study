package com.study.luxin.code.geektime.seven.day3;

import org.junit.Test;

public class MySearch {

    public boolean binarySearch(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int mid;
        while (true) {
            if (lo <= hi) {
                mid = (lo + hi) / 2;
                if (nums[mid] == target) {
                    return true;
                }

                if (nums[mid] > target) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                return false;
            }
        }
    }


    @Test
    public void test() {
        System.out.println(binarySearch(new int[]{1, 2, 4, 6, 7, 9}, 10));
    }


    /**
     * 获取第一个等于目标值的数
     *
     * @param nums
     * @param target
     * @return
     */
    public int findFirstTarget(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] >= target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (nums[lo] == target) {
            return lo;
        }

        if (lo + 1 < nums.length && nums[lo + 1] == target) {
            return lo + 1;
        }

        return -1;
    }

    @Test
    public void findFirstTest() {
        int[] nums = new int[]{1, 1, 1, 1, 1, 1, 2, 2};

        int firstTarget = findFirstTarget(nums, 2);
        System.out.println(firstTarget);
    }


}

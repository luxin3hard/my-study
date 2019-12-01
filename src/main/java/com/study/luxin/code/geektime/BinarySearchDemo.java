package com.study.luxin.code.geektime;

import org.junit.Test;

public class BinarySearchDemo {


    public int binarySearch(int[] nums, int t) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            // l + ( h - l) / 2 =  l + (h - l) >> 2
            int mid = l + (h - l) >> 2;
            if (nums[mid] == t) {
                return mid;
            }
            if (nums[mid] > t) {

                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

    @Test
    public void test() {
        int[] nums = new int[]{0, 2, 3};

        System.out.println(binarySearch(nums, 0));
        System.out.println(binarySearch(nums, 2));
        System.out.println(binarySearch(nums, 3));
        System.out.println(binarySearch(nums, 1));
        System.out.println(binarySearch(nums, 4));

    }


    public double sqrt(double a, int x) {
        double i = Math.pow(0.1D, x);

        double h = a;
        double l = 0D;

        double mid;
        while (true) {
            mid = (l + h) / 2;
            double v = mid * mid;
            if (v == a || (((mid - i) * (mid - i) < a) && ((mid + i) * (mid + i) > a))) {
                return mid;
            } else {

                if (v > a) {
                    h = mid;
                } else {
                    l = mid;
                }
            }
        }
    }

    @Test
    public void sqrtTest() {
        double sqrt = sqrt(3, 10);

        System.out.println(sqrt);
        System.out.println(sqrt * sqrt);


    }


}

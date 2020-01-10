package com.study.luxin.code.geektime.seven.day3;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class MySort {

    Random random = new Random();

    /**
     * 归并排序
     */
    public void mergeSort(int[] nums) {
        if (nums == null) {
            return;
        }
        int[] tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, tmp);
    }

    private void mergeSort(int[] nums, int st, int en, int[] tmp) {
        if (st == en) {
            return;
        }

        int mid = (st + en) / 2;
        mergeSort(nums, st, mid, tmp);
        mergeSort(nums, mid + 1, en, tmp);

        merge(nums, st, mid, en, tmp);
    }

    private void merge(int[] nums, int st, int mid, int en, int[] tmp) {

        int s = st, e = mid + 1;
        int k = 0;

        while (s <= mid && e <= en) {
            if (nums[s] <= nums[e]) {
                tmp[k++] = nums[s++];
            } else {
                tmp[k++] = nums[e++];
            }
        }

        if (s <= mid) {
            for (int i = s; i <= mid; i++) {
                tmp[k++] = nums[i];
            }
        }

        if (e <= en) {
            for (int i = e; i <= en; i++) {
                tmp[k++] = nums[i];
            }
        }

        int w = st;
        for (int i = 0; i < k; i++) {
            nums[w++] = tmp[i];
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 4, 0, 9, 7};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    //-------------------start---------------------------------------------------
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int lo, int hi) {
        if (lo < hi) {
            int p = randomPartition(nums, lo, hi);
            quickSort(nums, lo, p);
            quickSort(nums, p + 1, hi);
        }
    }

    private int partition(int[] nums, int lo, int hi) {

        //int s = Math.abs(random.nextInt()) % (hi - lo + 1) + lo;
        int t = nums[lo];
        int p = lo;
        int l = lo + 1, h = hi;

        while (l < h) {
            while (t <= nums[h] && l < h) {
                h--;
            }

            while (t > nums[l] && l < h) {
                l++;
            }

            if (l < h) {
                int w = nums[l];
                nums[l] = nums[h];
                nums[h] = w;
            }
        }

        if (nums[l] < t) {
            nums[p] = nums[l];
            nums[l] = t;
            p = l;
        }
        return p;
    }


    /**
     * 随机出现的目的是什么？
     * 如果数组是【1，2，3，4，5，6】，这样的数组，如果以1作为 flag，第一次只拍好了1，1后面的都没有排好。
     * 倒序【6，5，4，3，2，1】，第一次的结果是【1，5，4，3，2，6】
     * <p>
     * 思想就是：随机选择一个位置和flag交换，就不会出现有序这种情况了
     *
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    private int randomPartition(int[] nums, int lo, int hi) {
        int s = random.nextInt(hi - lo) + lo;
        swap(nums, lo, s);

        int t = nums[lo];
        int p = lo;
        int l = lo + 1, h = hi;

        while (l < h) {
            while (t <= nums[h] && l < h) {
                h--;
            }

            while (t > nums[l] && l < h) {
                l++;
            }

            if (l < h) {
                int w = nums[l];
                nums[l] = nums[h];
                nums[h] = w;
            }
        }

        if (nums[l] < t) {
            nums[p] = nums[l];
            nums[l] = t;
            p = l;
        }
        return p;
    }

    private void swap(int[] nums, int lo, int s) {
        int t = nums[s];
        nums[s] = nums[lo];
        nums[lo] = t;
    }
    //--------------------end--------------------------------------------------


    public void insertSort(int[] nums) {

        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int k = i - 1;

            while (k >= 0) {
                if (tmp[k] > nums[i]) {
                    k--;
                } else {
                    break;
                }
            }

            for (int j = i; j > k + 1; j--) {
                tmp[j] = tmp[j - 1];
            }

            tmp[k + 1] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = tmp[i];
        }
    }

    @Test
    public void insertSortTest() {
        int[] nums = new int[]{1, 3, 4, 2, 7};
        insertSort(nums);
        System.out.println(Arrays.toString(nums));

    }

    public void mPSort(int[] nums) {
        boolean exchanged = false;
        for (int i = nums.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;

                    exchanged = true;
                }
            }

            if (!exchanged) {
                break;
            }
        }
    }


    @Test
    public void mPSortTest() {
        int[] nums = new int[]{1, 3, 4, 2, 7};
        mPSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * nums是乱序的,找到第k大的数
     *
     * @param nums
     * @param k
     * @return
     */
    public int findK(int[] nums, int k) {
        if (k > nums.length) {
            return -1;
        }
        int t = nums.length - k;
        int lo = 0, hi = nums.length - 1;

        while (true) {
            int partition = partition(nums, lo, hi);
            if (partition == t) {
                return nums[partition];
            }

            if (partition > t) {
                hi = partition - 1;
            } else {
                lo = partition + 1;
            }
        }
    }

    @Test
    public void findKTest() {
        int[] nums = new int[]{1, 4, 2, 3};
        System.out.println(findK(nums, 3));
    }


}

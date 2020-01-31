package com.study.luxin.code.geektime.seven.day5;

import com.study.luxin.utils.MyUtils;
import org.junit.Test;

import java.util.Arrays;

public class HeapDemo {


    public void heapSort(int[] nums) {
        int[] heap = new int[nums.length + 1];
        buildHeap(nums, heap);

        for (int i = heap.length - 1; i >= 2; i--) {
            MyUtils.swap(heap, 1, i);
            adaptRootNode(heap, i - 1);
        }

        System.out.println(Arrays.toString(heap));
    }

    private void adaptRootNode(int[] heap, int maxIndex) {
        int i = 1;
        int left, right, bigIndex;

        while ((left = 2 * i) <= maxIndex) {
            right = left + 1;
            if (right <= maxIndex) {
                bigIndex = heap[left] >= heap[right] ? left : right;
            } else {
                bigIndex = left;
            }

            if (heap[i] < heap[bigIndex]) {
                MyUtils.swap(heap, i, bigIndex);
                i = bigIndex;
            } else {
                break;
            }
        }
    }


    private void buildHeap(int[] nums, int[] heap) {
        for (int i = 0; i < nums.length; i++) {
            adaptHeap(heap, i + 1, nums[i]);
        }
    }

    private void adaptHeap(int[] heap, int index, int num) {
        heap[index] = num;

        int t = index;
        int c;
        while ((c = (t / 2)) >= 1 && heap[t] > heap[c]) {
            MyUtils.swap(heap, t, c);
            t = c;
        }
    }


    @Test
    public void testBuildHeap() {
        int[] nums = new int[]{1, 4, 5, 9, 3, 7};
        int[] heap = new int[nums.length + 1];

        buildHeap(nums, heap);
        System.out.println(Arrays.toString(heap));
    }


    @Test
    public void testHeapSort() {
        int[] nums = new int[]{1, 2, 4, 5, 9, 3, 7};

        heapSort(nums);
    }


    public static class BigRootHeap {
        private int capacity;
        private int[] nums;
        private int size;


        public BigRootHeap(int size) {
            capacity = 0;
            this.size = size;
            nums = new int[size + 1];
        }
    }


}

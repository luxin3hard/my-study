package com.study.luxin.code.geektime.seven;

import com.study.luxin.code.LinkedNodeUtils;
import com.study.luxin.code.ListNode;
import org.junit.Test;

public class ArrayExercise {

    public int majorityElement(int[] nums) {

        int t = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                count++;
                t = nums[i];
            } else {
                if (t == nums[i]) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return t;
    }


    @Test
    public void test() {

        int[] n = new int[]{2, 1, 2, 2, 1};

        System.out.println(majorityElement(n));

    }


    /**
     * https://leetcode-cn.com/problems/first-missing-positive/solution/que-shi-de-di-yi-ge-zheng-shu-by-leetcode/
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        boolean findOne = false;

        int max = nums.length + 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                findOne = true;
            } else if (nums[i] > max || nums[i] < 1) {
                nums[i] = 1;
            }
        }

        if (!findOne) {
            return 1;
        }


        for (int num : nums) {
            if (num > 1) {
                nums[num - 1] = -nums[num - 1];
            } else {
                int t = -num;
                nums[t - 1] = -nums[t - 1];
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }


    @Test
    public void testFirstMissingPositive() {

        int[] nums = new int[]{3, 4, 5, 1, 2};
        int firstMissingPositive = firstMissingPositive(nums);

        System.out.println(firstMissingPositive);
    }


    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode t = head;
        ListNode w = head;

        while (true) {
            if (t == null) {
                return false;
            } else {
                t = t.next;
            }

            if (w.next == null || w.next.next == null) {
                return false;
            } else {
                w = w.next.next;
            }
            if (t == w) {
                return true;
            }
        }
    }

    @Test
    public void cycleTest() {
        ListNode of = LinkedNodeUtils.of(1, 2);

        System.out.println(hasCycle(of));

    }


    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int lo, int hi) {
        if (lo == hi) {
            return lists[lo];
        }

        int mid = (lo + hi) / 2;

        ListNode node = mergeKLists(lists, lo, mid);
        ListNode node1 = mergeKLists(lists, mid + 1, hi);
        return merge(node, node1);
    }

    private ListNode merge(ListNode node, ListNode node1) {
        if (node == null) {
            return node1;
        }

        if (node1 == null) {
            return node;
        }
        ListNode t = new ListNode(-1);
        ListNode k = t;

        ListNode<Integer> w = node, q = node1;
        while (w != null && q != null) {
            if (w.val < q.val) {
                k.next = new ListNode(w.val);
                w = w.next;
            } else {
                k.next = new ListNode(q.val);
                q = q.next;
            }
            k = k.next;
        }

        if (w != null) {
            k.next = w;
        }

        if (q != null) {
            k.next = q;
        }
        return t.next;
    }


    @Test
    public void testMerge() {
        ListNode of = LinkedNodeUtils.of(1, 4, 5);
        ListNode of1 = LinkedNodeUtils.of(1, 3, 4);
        ListNode of2 = LinkedNodeUtils.of(2, 6);

        ListNode[] array = new ListNode[]{of, of1, of2};

        ListNode node = mergeKLists(array);

        System.out.println(LinkedNodeUtils.values2Str(node));
    }

}

package com.study.luxin.code;

import org.junit.Test;

import java.util.*;

/**
 * Created by lx on 22/04/2018.
 * https://leetcode-cn.com/problems/two-sum/description/
 */

public class TowSum {

    // https://leetcode-cn.com/problems/two-sum/description/
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> needNum = new HashMap();
        int[] towNum = new int[2];

        for (int i = 0; i < nums.length; i++) {
            Integer tmp = needNum.get(nums[i]);
            if (tmp != null) {
                towNum[1] = i;
                towNum[0] = tmp;
                return towNum;
            } else {
                needNum.put(target - nums[i], i);
            }
        }
        return null;
    }


    @Test
    public void test() {
        int[] nums = new int[]{2, 0, 23, 7, 11, 15};

        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));

    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode header = l1;
        ListNode last = l1;
        boolean up = false;

        while (up || (l1 != null && l2 != null)) {
            if (up) {
                if (l1 != null && l2 != null) {

                    int sum = l1.val + l2.val + 1;
                    if (sum >= 10) {
                        l1.val = sum - 10;
                        up = true;
                    } else {
                        l1.val = sum;
                        up = false;
                    }

                    last = l1;
                    l1 = l1.next;
                    l2 = l2.next;
                    continue;
                } else if (l1 != null && l2 == null) {
                    if (l1.val + 1 == 10) {
                        l1.val = 0;
                        up = true;

                        ListNode tmp = last;
                        tmp.next = l1;
                        last = l1;
                        l1 = l1.next;
                    }
                    continue;
                } else if (l1 == null && l2 != null) {
                    if (l2.val + 1 == 10) {
                        l2.val = 0;
                        up = true;

                        ListNode tmp = last;
                        tmp.next = l2;
                        last = l2;
                        l2 = l2.next;
                    }
                    continue;
                } else {
                    last.next = new ListNode(1);
                    up = false;
                    continue;
                }
            } else {
                if (l1 != null && l2 != null) {
                    int sum = l1.val + l2.val;
                    if (sum >= 10) {
                        l1.val = sum - 10;
                        up = true;
                    } else {
                        l1.val = sum;
                        up = false;
                    }

                    last = l1;
                    l1 = l1.next;
                    l2 = l2.next;
                    continue;
                } else if (l1 == null) {
                    last.next = l2;
                } else {
                    last.next = l1;
                }
            }
        }

        if (l2 != null) {
            last.next = l2;
        }

        return header;
    }


    @Test
    public void addTwoNumbersTest() {
        ListNode l1 = new ListNode(5);
   /*     ListNode l11 = new ListNode(4);
        l1.next = l11;
        ListNode l12 = new ListNode(3);
        l11.next = l12;*/


        ListNode l2 = new ListNode(5);
        ListNode l21 = new ListNode(9);
        l2.next = l21;
        ListNode l22 = new ListNode(9);
        l21.next = l22;

        ListNode l23 = new ListNode(9);
        l22.next = l23;


        printNode(l1);
        printNode(l2);

        printNode(addTwoNumbers(l2, l1));

    }


    private void printNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }

        System.out.println();
    }


    // 两个大数相加
    private int[] towBigNumSum(int[] s1, int[] s2) {
        int carry = 0;
        int p1 = s1.length - 1;
        int p2 = s2.length - 1;

        int[] result = new int[p1 + p2 + 1];
        int j = p1 + p2;

        while (carry > 0 || p1 >= 0 || p2 >= 0) {
            int sum = (p1 >= 0 ? s1[p1] : 0) + (p2 >= 0 ? s2[p2] : 0) + carry;
            result[j--] = sum % 10;
            if (sum >= 10) {
                carry = 1;
            } else {
                carry = 0;
            }
            p1--;
            p2--;
        }
        System.out.println(Arrays.toString(result));
        return result;
    }


    private int[] towSum02(int[] nums, int target) {
        Map<Integer, Integer> remainder2index = new HashMap<>();
        int index = 0;
        for (int num : nums) {
            remainder2index.put(target - num, index++);
        }

        int[] result = new int[2];

        int index1 = 0;
        for (int num : nums) {
            Integer index2 = remainder2index.get(num);
            if (index2 != null && index1 != index2) {
                result[0] = index1;
                result[1] = index2;
                return result;
            }
            index1++;
        }
        return null;
    }

    @Test
    public void test2() {
        int[] nums = new int[]{3, 2, 4};
        System.out.println(Arrays.toString(towSum02(nums, 6)));
    }


}

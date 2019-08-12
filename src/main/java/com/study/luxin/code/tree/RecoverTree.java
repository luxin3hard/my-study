package com.study.luxin.code.tree;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 */

public class RecoverTree {

    /**
     * 思路:数组初始是按照中序遍历的,先构建成书; 然后按照线序遍历,先序遍历的结果就是从小到大的顺序
     * 然后一次循环,左边比右边小,找出不符合规则的下表,然后交换
     */


    public TreeNode buildTree(int[] array) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int p = 1;
        TreeNode head = new TreeNode(array[0]);
        queue.add(head);


        buildTree(p, queue, array);
        return head;
    }

    private void buildTree(int p, LinkedList<TreeNode> queue, int[] array) {
        while (queue.peek() != null && p < array.length) {
            TreeNode node = queue.poll();

            node.left = new TreeNode(array[p++]);
            queue.add(node.left);

            if (p < array.length) {
                node.right = new TreeNode(array[p++]);
                queue.add(node.right);
            }
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}

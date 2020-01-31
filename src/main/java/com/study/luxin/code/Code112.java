package com.study.luxin.code;

import org.junit.Test;

public class Code112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        Boolean[] has = new Boolean[1];

        has[0] = false;
        if (root == null) {
            return false;
        } else {
            if (root.left == null && root.right == null) {
                return root.val == sum;
            } else {
                pathSum(root.left, sum, has, root.val);
                pathSum(root.right, sum, has, root.val);
                return has[0];
            }
        }
    }

    private void pathSum(TreeNode node, int sum, Boolean[] has, int val) {
        if (!has[0] && node != null) {
            int newValue = val + node.val;
            if (node.left == null && node.right == null) {
                has[0] = (newValue == sum);
            } else {
                if (node.left != null) {
                    pathSum(node.left, sum, has, newValue);
                }
                if (node.right != null) {
                    pathSum(node.right, sum, has, newValue);
                }
            }
        }
    }

    @Test
    public void test() {
        TreeNode fi = new TreeNode(1);
        TreeNode fo = new TreeNode(2);

        fi.left = fo;
        System.out.println(hasPathSum(fi, 1));
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

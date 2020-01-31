package com.study.luxin.code;


public class Code226 {

    public TreeNode invertTree(TreeNode root) {

        if (root != null) {
            TreeNode temp = root.left;

            root.left = root.right;
            root.right = temp;

            invertTree(root.right);
            invertTree(root.left);

        }

        return root;
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

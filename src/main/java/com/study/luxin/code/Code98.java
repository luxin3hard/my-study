package com.study.luxin.code;

import org.junit.Test;

public class Code98 {
    public boolean isValidBST(TreeNode root) {


        Object[] a = new Object[2];
        a[1] = true;
        ergodic(root, a);

        return (boolean) a[1];

    }

    private void ergodic(TreeNode root, Object[] a) {
        if (root != null && (boolean) a[1]) {
            ergodic(root.left, a);
            if (a[0] == null) {
                a[0] = root.val;
            } else {
                if ((int) a[0] >= root.val) {
                    a[1] = false;
                } else {
                    a[0] = root.val;
                }
            }
            ergodic(root.right, a);
        }
    }

    @Test
    public void test() {

        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(1);
        a.left = b;

        isValidBST(a);
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

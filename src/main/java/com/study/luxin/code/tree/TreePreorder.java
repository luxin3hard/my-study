package com.study.luxin.code.tree;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class TreePreorder {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private void preorder(TreeNode header, List<Integer> values) {
        if (header != null) {
            values.add(header.val);

            preorder(header.left, values);
            preorder(header.right, values);
        }
    }


    private void midOrder(TreeNode header, List<Integer> values) {
        if (header != null) {
            midOrder(header.left, values);
            values.add(header.val);
            midOrder(header.right, values);
        }
    }


    @Test
    public void preorderTest() {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;


        List<Integer> l1 = Lists.newArrayList();
        List<Integer> l2 = Lists.newArrayList();

        preorder(a, l1);
        midOrder(a, l2);

        System.out.println(l1);
        System.out.println(l2);
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


}

package com.study.luxin.code.geektime.seven.day5;

import org.junit.Test;

public class BinarySearchTree {

    private Node root;

    public void insert(int data) {
        if (root == null) {
            root = new Node(data);
        } else {

            Node t = root;
            while (true) {
                if (t.data > data) {
                    if (t.left == null) {
                        t.left = new Node(data);
                        return;
                    } else {
                        t = t.left;
                    }
                } else {
                    if (t.right == null) {
                        t.right = new Node(data);
                        return;
                    } else {
                        t = t.right;
                    }
                }
            }
        }
    }


    public void remove(int target) {
        Node t = root;

        Node parent = null;
        while (true) {
            if (t.data == target) {
                if (t.right == null && t.left == null) {
                    if (parent == null) {
                        root = null;
                    } else {
                        if (t == parent.left) {
                            parent.left = null;
                        } else {
                            parent.right = null;
                        }
                    }

                    return;
                } else if (t.right != null) {
                    Node w = t.right;
                    Node p = null;
                    while (w.left != null) {
                        p = w;
                        w = w.left;
                    }

                    if (t == root) {
                        w.right = root.right;
                        w.left = root.left;
                        root = w;
                    } else {
                        w.left = t.left;
                        if (parent.right == t) {
                            parent.right = w;
                        } else {
                            parent.left = w;
                        }
                    }

                    if (p != null) {
                        p.left = null;
                    }

                    return;
                } else {
                    Node w = t.left;
                    Node p = null;
                    while (w.right != null) {
                        p = w;
                        w = w.right;
                    }

                    if (t == root) {
                        w.right = root.right;
                        w.left = root.left;
                        root = w;
                    } else {
                        w.left = t.left;

                        if (parent.right == t) {
                            parent.right = w;
                        } else {
                            parent.left = w;
                        }
                    }
                    if (p != null) {
                        p.right = null;
                    }

                    return;
                }
            } else {
                parent = t;
                if (t.data > target) {
                    t = t.left;
                } else {
                    t = t.right;
                }
            }
        }
    }

    @Test
    public void test() {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        tree.insert(3);
        tree.insert(5);

        tree.remove(4);
        //System.out.println(tree);

        preOrder(tree.root);
    }

    public void preOrder(Node n) {
        if (n != null) {
            preOrder(n.left);
            System.out.println(n.data);
            preOrder(n.right);
        }
    }

    private static class Node {
        Node left;
        Node right;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }


}

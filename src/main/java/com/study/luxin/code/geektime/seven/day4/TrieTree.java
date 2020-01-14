package com.study.luxin.code.geektime.seven.day4;

import lombok.Data;

public class TrieTree {

    private Node root;

    public TrieTree() {
        root = new Node('/');
    }

    public void insert(String str) {
        char[] chars = str.toCharArray();
        Node t = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            Node child = t.children[index];

            if (child == null) {
                t.children[index] = new Node(chars[i]);
            }
            t = t.children[index];
        }

        t.setEndChar(true);
    }


    public boolean find(String str) {
        char[] chars = str.toCharArray();
        Node t = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            Node child = t.children[index];
            if (child == null) {
                return false;
            } else {
                t = t.children[index];
            }
        }
        return t.isEndChar();
    }

    @Data
    private static class Node {
        private Node[] children = new Node[26];
        private char data;
        private boolean endChar;

        public Node(char data) {
            this.data = data;
        }
    }


}

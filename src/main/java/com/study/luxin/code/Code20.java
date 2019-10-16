package com.study.luxin.code;

import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class Code20 {
    public boolean isValid(String s) {
        if (s == null || s.equals("")) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        char[] stack = new char[s.length() / 2 + 1];
        int top = -1;
        for (int i = 0; i < s.length() && top < s.length() / 2 + 1; i++) {
            char c = s.charAt(i);
            if (top != -1 && match(stack[top], c)) {
                top--;
            } else {

                if (++top == s.length() / 2) {
                    return false;
                }
                stack[top] = c;
            }
        }

        if (top == -1) {
            return true;
        }
        return false;
    }

    boolean match(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }


    @Test
    public void test() {
        assert isValid("()");
        assert isValid("()[]{}");
        assert isValid("({[()]})");
        assert !isValid("([})");

    }
}

package com.study.luxin.code.geektime;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

public class StringMatchDemo {
    /**
     * brute force. 朴素匹配算法.
     * <p>
     * 核心思想: 模式串(长度m)和 主串(长度n) 从起始位置一点点匹配到主串结尾.需要执行n-m+1次
     * <p>
     * 时间复杂度: O(mn)
     * 空间复杂度: O(1)
     */
    public int bruteForceStrMatch(String b, String t) {
        int n = b.length();
        int m = t.length();
        for (int i = 0; i < n - m + 1; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (b.charAt(i + j) != t.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void bfTest() {
        System.out.println(bruteForceStrMatch("abdabc", "abc"));
    }

    /**
     * Rabin-Karp 算法的思想: 是BF算法的升级版. 将n-m+1个串都生成hash值,然后在和模式串比较. hash值相等,就代表相等.
     * <p>
     * hash值特殊处理 十进制字符在"1234"=1*1000+2*100+3*10+4*1. 将字符串当做26进制的数 "abc"=a*26*26 + b*26 + c
     * 需要注意用 char-'a' 得到的数据是 26个字母代表0-25,使用26进制没问题
     *
     * @param b
     * @param t
     * @return
     */
    public int rabinKarpStringMatch(String b, String t) {
        int n = b.length();
        int m = t.length();

        int hash = 1;

        int baseHash = b.charAt(0) - 'a', targetHash = t.charAt(0) - 'a';
        for (int i = 1; i < m; i++) {
            baseHash = baseHash * 26 + (b.charAt(i) - 'a');
            targetHash = targetHash * 26 + (t.charAt(i) - 'a');
            hash = hash * 26;
        }

        if (baseHash == targetHash) {
            return 0;
        }

        for (int i = m; i < n; i++) {
            baseHash = (baseHash - (b.charAt(i - m) - 'a') * hash) * 26 + b.charAt(i) - 'a';
            if (baseHash == targetHash) {
                return i - m + 1;
            }
        }
        return -1;
    }

    @Test
    public void rkTest() {
        System.out.println(rabinKarpStringMatch("abdabc", "abc"));
    }


    /**
     * Boyer-Moore 算法的思想:
     * 主  串: abcdabcaaaa
     * 模式串: abd
     * 发现c不在模式串里面,c无论如何都不会匹配,那么直接移到c之后.
     */
    public int boyerMooreStringMatch(String b, String t) {
        int n = b.length();
        int m = t.length();

        Set<Character> targetSet = Sets.newHashSetWithExpectedSize(m);
        for (int i = 0; i < m; i++) {
            targetSet.add(t.charAt(i));
        }

        for (int i = 0; i < n - m + 1; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                char base = b.charAt(i + j);

                if (!targetSet.contains(base)) {
                    i = i + m;
                    match = false;
                    break;
                }
                char target = t.charAt(j);
                if (base != target) {
                    match = false;
                    break;
                }
            }

            if (match) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void bmTest() {
        assert boyerMooreStringMatch("abcdabdaaaa", "abd") == 4;
    }


}

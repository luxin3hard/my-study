package com.study.luxin.code.geektime.seven;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

public class ArrayLinkedDemo {

    @Test
    public void test() {
        AutoAddArray<Integer> autoAddArray = new AutoAddArray<Integer>(Integer.class, 1);

        autoAddArray.add(1);
        autoAddArray.add(2);
        autoAddArray.add(3);

        System.out.println(Arrays.toString(autoAddArray.array));
    }

    @Test
    public void test01() {
        MyArray<Integer> myArray = new MyArray<>(Integer.class, 3);
        myArray.add(1);
        myArray.add(2);
        myArray.add(3);
        System.out.println(Arrays.toString(myArray.array));

        myArray.remove(1);
        myArray.remove(1);

        myArray.add(4);

        System.out.println(myArray);
        System.out.println(myArray.get(1));
    }

    public int[] mergeSortNums(int[] a, int[] b) {

        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        int i = 0, j = 0, k = 0;

        int[] r = new int[a.length + b.length];

        while (i < a.length && j < b.length) {
            int ta = a[i];
            int tb = b[j];

            if (ta <= tb) {
                r[k++] = ta;
                i++;
            } else {
                r[k++] = tb;
                j++;
            }
        }

        if (i < a.length) {
            for (int l = i; l < a.length; l++) {
                r[k++] = a[l];
            }
        }

        if (j < b.length) {
            for (int l = j; l < b.length; l++) {
                r[k++] = b[l];
            }
        }
        return r;
    }

    @Test
    public void mergeTest() {
        int[] ints = mergeSortNums(new int[]{4, 9}, new int[]{3, 5, 7, 10, 11});

        System.out.println(Arrays.toString(ints));
    }

    public void sum3(int target, int[] n) {

        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < n.length; i++) {
            map.put(target - n[i], i);
        }


        for (int i = 0; i < n.length; i++) {


        }


    }

    static class AutoAddArray<T> {
        private int length;
        private T[] array;
        private int capacity;

        public AutoAddArray(Class<T> componentType, int length) {
            this.length = length;
            capacity = 0;
            array = (T[]) Array.newInstance(componentType, length);
        }


        public void add(T value) {
            if (capacity < length) {
                array[capacity++] = value;
            } else {
                T[] tmp = array;

                int tmpLength = length;
                length = length * 2;
                T[] newArray = (T[]) Array.newInstance(value.getClass(), length);
                System.arraycopy(tmp, 0, newArray, 0, tmpLength);
                newArray[capacity++] = value;
                this.array = newArray;
            }
        }
    }

    /**
     * 固定大小,支持动态的增加/删除/修改 等操作
     */
    class MyArray<T> {
        private T[] array;
        private int length;
        private int capacity;
        private byte[] deleteArray;
        private int deleteCount;

        private int[] deletedNums;

        public MyArray(Class<T> componentType, int length) {
            this.length = length;
            capacity = 0;
            array = (T[]) Array.newInstance(componentType, length);
            deleteArray = new byte[length];
            deletedNums = new int[length];
            deleteCount = 0;
        }

        public void add(T value) {
            if (capacity < length) {
                array[capacity++] = value;
            } else {
                if (deleteCount > 0) {
                    capacity = capacity - deleteCount;

                    for (int i = 0; i < deleteArray.length; i++) {
                        if (deleteArray[i] != 1 && deletedNums[i] > 0) {

                            array[i - deletedNums[i]] = array[i];
                            array[i] = null;
                        }

                        if (deleteArray[i] == 1) {
                            array[i] = null;
                        }

                        deletedNums[i] = 0;
                        deleteArray[i] = 0;
                    }
                }
                array[capacity++] = value;
            }
        }


        @Override
        public String toString() {
            String str = "";
            for (int i = 0; i < length; i++) {

                if (deleteArray[i] != 1 && array[i] != null) {
                    str = str + " " + array[i];
                }
            }

            return str;
        }

        public void remove(int k) {
            deleteCount++;
            if (k >= length) {
                throw new RuntimeException("超出了最大长度");
            }

            int t = k;
            k = deletedNums[k] + k;


            if (k > capacity) {
                return;
            }

            deleteArray[k] = 1;
            for (int i = t; i < length; i++) {
                deletedNums[i]++;
            }
        }

        public T get(int k) {
            if (k >= length) {
                throw new RuntimeException("超出了最大长度");
            }

            k = deletedNums[k] + k;

            if (deleteArray[k] == 1) {
                return null;
            } else {
                return array[k];
            }
        }
    }


}

package com.study.luxin.code.geektime.seven.day4;

import org.junit.Test;

import java.util.Objects;

public class MyHash {

    @Test
    public void test() {

        MyHashSet<Integer> myHashSet = new MyHashSet<>();
        myHashSet.add(1);
        myHashSet.add(2);
        myHashSet.add(4);
        myHashSet.add(7);
        myHashSet.add(9);
        myHashSet.add(10);
        myHashSet.add(12);
    }

    public static class MyHashSet<T> {

        private int size;
        private Entity[] table;
        private double ratio = 0.75D;
        private int capacity;

        /**
         * 默认创建16大小的数组
         */
        public MyHashSet() {
            size = 4;
            table = new Entity[size];
        }

        public void add(T v) {
            capacity++;

            if (capacity > size * ratio) {
                int tmpSize = size << 1;
                Entity[] temp = new Entity[tmpSize];

                for (int i = 0; i < table.length; i++) {
                    Entity<T> t = table[i];

                    while (t != null) {
                        boolean same = false;
                        int position = findPosition(t.value, tmpSize);
                        Entity entity = temp[position];
                        if (entity == null) {
                            temp[position] = t;
                            t = t.next;
                            temp[position].next = null;
                        } else if (Objects.equals(entity.value, t.value)) {
                            continue;
                        } else {
                            while (entity.next != null) {
                                // 注意,如果迁移的值如果已经存在,那么就不需要将数据放置到链表最后了
                                if (Objects.equals(entity.value, t.value)) {
                                    same = true;
                                    break;
                                }
                                entity = entity.next;
                            }

                            if (same) {
                                t = t.next;
                                continue;
                            }
                            entity.next = t;
                            t = t.next;
                            entity.next.next = null;
                        }
                    }
                }

                size = tmpSize;
                table = temp;
            }

            int p = findPosition(v, size);
            Entity value = table[p];
            if (value == null) {
                table[p] = new Entity(v);
                return;
            } else {
                boolean same = false;
                while (value.next != null) {
                    if (Objects.equals(value.value, v)) {
                        same = true;
                        break;
                    }
                    value = value.next;
                }

                if (same) {
                    return;
                } else {
                    value.next = new Entity(v);
                }
            }
        }

        private int findPosition(T value, int size) {
            return value.hashCode() % (size - 1);
        }

        static class Entity<T> {
            T value;
            Entity next;

            public Entity(T v) {
                this.value = v;
            }
        }
    }


}

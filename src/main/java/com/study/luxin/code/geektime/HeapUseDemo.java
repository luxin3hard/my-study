package com.study.luxin.code.geektime;

public class HeapUseDemo {
    /**
     * 动态数据求中位数
     *
     * 1. 线上数据排序
     * 2. 将数据分为两半,[1,n+1/2]变成一个大顶堆 [n+1/2+1,n]变成小顶堆
     * 3. 插入数据,如果小于大顶堆的root,那么将数据加入到大顶堆,n=n+1 size=size+1.  如果数据大顶堆 size >n+1/2,将root 加入到小顶堆.
     * 4. 同理,如果新的数据大于小顶堆的root,那么将数据加入到小顶堆,也要维持数据 size<=n/2 这个特性
     */


}

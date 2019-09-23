package com.study.luxin.reference;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.List;

public class MyReference {


    @Test
    public void referenceTest() {
        // 强引用,instance指向new Object的内存块假如是10000
        Object instance = new Object();
        // 对instance的软引用,垃圾回收不会回收instance因为上面对它是强引用
        // 软引用对象指向10000
        SoftReference<Object> softReference = new SoftReference<Object>(instance);

        // instance->null
        instance = null;

        // softReference对象的引用对象->10000,现在变成了软引用.
        Object ref = softReference.get();
    }


    @Test
    public void softReferenceRecycledBeforeOutOfMem() {
        SoftReference<Object> softReference = new SoftReference<Object>(new Object());
        List<Integer> list = Lists.newArrayList();
        try {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
        Object instance = softReference.get();
        Assert.assertNull(instance);
    }


    @Test
    public void stronglyReachableSoftReferenceTest() {
        SoftReference<Object> softReference = new SoftReference<Object>(new Object());

        // 强可达
        Object instance = softReference.get();

        List<Integer> list = Lists.newArrayList();
        try {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }

        Assert.assertNotNull(softReference.get());
        // 强可达,且被使用,是不会被清除的
        System.out.println(instance);
    }


    @Test
    /**
     * 引用的引用测试,一个对象真正占用的内存空间?
     * 如果一个对象分配的内存空间包含 其field对象的空间,那么 当ReferenceEntity被回收的时候,field也应该不能获取,但是得到的结果却是不一样的.
     */
    public void subReferenceStronglyReachable() {
        SoftReference<ReferenceEntity> softReference = new SoftReference<ReferenceEntity>(new ReferenceEntity(new Object()));

        // 强可达
        Object instance = softReference.get().getObject();

        List<Integer> list = Lists.newArrayList();
        try {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }

        System.out.println(softReference.get());
        // 强可达,且被使用,是不会被清除的
        System.out.println(instance);
    }


    @Test
    /**
     * 弱引用将被回收.当发生gc的时候
     */
    public void weakReferenceWillRecovery() {

        WeakReference<ReferenceEntity> referenceEntityWeakReference = new WeakReference<ReferenceEntity>(new ReferenceEntity(new Object()));
        System.out.println(referenceEntityWeakReference.get());
        System.gc();
        System.out.println(referenceEntityWeakReference.get());
    }


    @Test
    /**
     * 弱引用对象(该对象是指的)referent被强引用引用的时候,不会被回收
     */
    public void weakReferenceWillRecoveryThroughStronglyReachable() {

        WeakReference<ReferenceEntity> referenceEntityWeakReference = new WeakReference<ReferenceEntity>(new ReferenceEntity(new Object()));

        ReferenceEntity referenceEntity = referenceEntityWeakReference.get();
        System.out.println(referenceEntity);
        System.gc();
        System.out.println(referenceEntityWeakReference.get());
        System.out.println(referenceEntity);
    }

    @Test
    /**
     * weakReference被强引用引用的时候,不会影响 referent的回收
     */
    public void weakReferenceWillRecoveryThroughStronglyReachable1() {
        WeakReference<ReferenceEntity> referenceEntityWeakReference = new WeakReference<ReferenceEntity>(new ReferenceEntity(new Object()));

        Object referenceEntity = referenceEntityWeakReference;
        System.out.println(referenceEntity);
        System.gc();
        System.out.println(referenceEntityWeakReference.get());
        System.out.println(referenceEntity);
    }










}

package com.wkcto.atomics.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicIntegerArray的基本操作
 * 原子更新数组
 */
public class Test {
    public static void main(String[] args) {

        //1)船舰一个指定长度的原子数组
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        System.out.println(atomicIntegerArray);//[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

        //返回指定位置的元素
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(atomicIntegerArray.get(1));

        //设置指定位置的元素
        atomicIntegerArray.set(0,10);//(index,value)

        //在设置数组元素的新值时，同时返回数组元素的旧值
        System.out.println(atomicIntegerArray.getAndSet(1,11));//返回0，设置index 1为11
        System.out.println(atomicIntegerArray);//[10, 11, 0, 0, 0, 0, 0, 0, 0, 0]

        //修改数组元素的值，把数组元素加上某个值
        System.out.println(atomicIntegerArray.addAndGet(0,22));//index 0 为 10， 加上22等于32， 返回32
        System.out.println(atomicIntegerArray.getAndAdd(1,33));//index 1 为 11， 先返回旧值11， 再加上33
        System.out.println(atomicIntegerArray);

        //CAS操作
        //如果数组中索引值为0的元素的值时32，就修改为222
        System.out.println(atomicIntegerArray.compareAndSet(0,32,222));//index 0 为 32， 替换成功返回true
        System.out.println(atomicIntegerArray);//[222, 44, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(atomicIntegerArray.compareAndSet(1,11,333));//index 1 为 44， 没有替换成功返回false
        System.out.println(atomicIntegerArray);//[222, 44, 0, 0, 0, 0, 0, 0, 0, 0]

        //自增/自减
        System.out.println(atomicIntegerArray.incrementAndGet(0));//先加一再返回 --> 223
        System.out.println(atomicIntegerArray);//[223, 44, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(atomicIntegerArray.getAndIncrement(1));//先返回再加一 --> 44
        System.out.println(atomicIntegerArray);//[223, 45, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(atomicIntegerArray.decrementAndGet(2));//先减一再返回 --> -1
        System.out.println(atomicIntegerArray);//[223, 45, -1, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(atomicIntegerArray.getAndDecrement(3));//先返回再减一 --> 0
        System.out.println(atomicIntegerArray);//[223, 45, -1, -1, 0, 0, 0, 0, 0, 0]
    }

}

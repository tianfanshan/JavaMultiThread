package com.wkcto.threadmethod.p6setPriority;

/**
 * 使用.setPriority()来设置线程的优先级，最高位10，最低为1
 * 并不是优先级越高的线程优先于低优先级的线程运行
 * 这里的优先级指的是优先级越高的线程能分配到更多的CPU资源
 * 滥用优先级可能会导致线饥饿
 * 线程的优先级具有继承性(子线程继承父线程的优先级)
 * 一般开发中使用不到设置线程优先级
 */
public class Test {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.setPriority(1);
        threadA.start();

        ThreadB threadB = new ThreadB();
        threadB.setPriority(10);
        threadB.start();
    }
}

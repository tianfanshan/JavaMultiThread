package com.wkcto.threadmethod.p1currentThread;

/**
 * 测试当前线程
 * 当前线程就是调用代码的线程
 */
public class Test01CurrentThread {
    public static void main(String[] args) {
        System.out.println("main方法中打印当前线程： " + Thread.currentThread().getName());

        //创建子线程,调用SubThread构造方法，在main线程中调用构造方法，
        // 所以构造方法中的当前线程就是main线程,
        // subThread构造方法中的打印当前线程为main而不是Thread-0
        SubThread subThread = new SubThread();
//        subThread.start(); //启动子线程：打印的当前线程是Thread-0
        subThread.run();//使用run方法来启动子线程则会在当前线程中执行，因为没有开启新的线程，打印的线程为main而不是Thread-0
    }
}

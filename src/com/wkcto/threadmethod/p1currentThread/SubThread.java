package com.wkcto.threadmethod.p1currentThread;

/**
 * 获得当前线程的名称
 * 定义线程类
 * 分别在构造方法中和run方法中打印线程名称
 */
public class SubThread extends Thread {

    public SubThread(){
        System.out.println("构造方法打印当前线程的名称： " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run方法打印当前线程的名称: " + Thread.currentThread().getName());
    }
}

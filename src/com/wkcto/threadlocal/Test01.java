package com.wkcto.threadlocal;

/**
 * ThreadLocal的基本使用
 */
public class Test01 {
    //定义ThreadLocal对象
    private static ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();

        t1.start();
        t2.start();
    }

    public static class SubThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 20 ; i ++){
                //设置线程关联的值
                threadLocal.set(Thread.currentThread().getName() + " - " + i);
                //调用get()方法读取关联的值
                System.out.println(Thread.currentThread().getName() + " value = " + threadLocal.get());
            }
        }
    }
}

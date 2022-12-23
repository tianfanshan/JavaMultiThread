package com.wkcto.createthread.p3;

/**
 * 当线程类已经有父类了，就不能用继承Thread类的形式创建线程，可以使用时间Runnable接口的形式
 * 1)定义类实现Runnable接口
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++){
            System.out.println("sub thread --> " + i);
        }
    }
}

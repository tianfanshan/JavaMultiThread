package com.wkcto.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁的可重入性
 */
public class Test04 {
    static class SubThread extends Thread{
//        private Lock lock = new ReentrantLock();//定义锁对象,但是因为时实例变量所以这个锁是各自线程自己的实例变量，而不是共享的锁，所以会产生线程安全问题
        private static final Lock lock = new ReentrantLock();//这里我们加上static，让他成为静态变量，是所有的静态方法都可以访问，就不会产生线程安全问题
        public static int num = 0;//定义变量
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                try {
                    //可重入锁指可以反复获得该锁
                    lock.lock();
                    lock.lock();
                    num++;
                }finally {
                    lock.unlock();
                    lock.unlock();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(SubThread.num);
    }
}

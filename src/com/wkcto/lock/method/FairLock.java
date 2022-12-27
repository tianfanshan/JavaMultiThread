package com.wkcto.lock.method;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁实现成本高，性能低，如果不是有特别的需求，一般不使用公平锁
 */
public class FairLock {
//    static ReentrantLock lock = new ReentrantLock();//默认是非公平锁
    static ReentrantLock lock = new ReentrantLock(true);//传入参数true设置为公平锁

    public static void main(String[] args) {
        int num;
        boolean flag = true;
        Runnable runnable = new Runnable() {
            int num;
            boolean flag = true;
            @Override
            public void run() {
                while (flag){
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + " 获得了锁");
                        num++;
                    }finally {
                        lock.unlock();
                    }
                    if (num == 10){
                        flag = false;
                    }
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
        /*
            运行程序
                1)如是果非公平锁，系统会倾向于让一个线程再次获得已经持有的锁，这种分配策略时高效的，但非公平的
                2)如果是公平锁，多个线程不会发生同一个线程连续多次获得锁,能够保证锁的公平性
         */
    }
}

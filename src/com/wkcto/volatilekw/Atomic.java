package com.wkcto.volatilekw;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用源自类进行自增
 */
public class Atomic {
    public static void main(String[] args) {
        //在main线程中创建100个子线程
        for (int i = 0; i < 100; i++){
            new MyThread().start();
        }
        try {
            Thread.sleep(1000);
            System.out.println(MyThread.count.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    static class MyThread extends Thread {
        //使用AtomicInteger对象
        private static final AtomicInteger count = new AtomicInteger();

        public static void addCount() {
            for (int i = 0; i < 1000;i++){
                //使用AtomicInteger实现自增的后缀形式
                count.getAndIncrement();
            }
            System.out.println(Thread.currentThread().getName() + " count: " + count.get());
        }

        @Override
        public void run() {
            addCount();
        }
    }
}

package com.wkcto.wait;

/**
 * notify()通知过早
 */
public class Test08 {

    private final static Object LOCK = new Object();//定义对象作为锁对象

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK){
                    try {
                        System.out.println("begin wait...");
                        LOCK.wait();
                        System.out.println("end wait...");
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK){
                    System.out.println("begin notify...");
                    LOCK.notify();
                    System.out.println("end notify...");
                }
            }
        });

        //如果先开启t1线程，再开启t2线程，大多数情况下，t1先等待，t2再把t1唤醒(线程的启动先后顺序不由代码顺序决定，他们是并发的)
//        t1.start();
//        t2.start();

        //如果先开启t2通知线程，再开启t1等待线程，可能会出现t1线程等待没有收到通知的情况(线程的启动先后顺序不由代码顺序决定，他们是并发的)
        t2.start();
        t1.start();

    }
}

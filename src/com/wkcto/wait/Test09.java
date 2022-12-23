package com.wkcto.wait;

/**
 * notify()通知过早，就不让线程等待了
 */
public class Test09 {

    private final static Object LOCK = new Object();//定义对象作为锁对象
    private static boolean isFirst = true;//鼎泰变量作为是否第一个运行的线程标志

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK){
                    if (isFirst){
                        try {
                            System.out.println("begin wait...");
                            LOCK.wait();
                            System.out.println("end wait...");
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
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
                    isFirst = false;//如果t2先运行就把第一个线程标志修改为false
                }
            }
        });

        //如果先开启t1线程，再开启t2线程，大多数情况下，t1先等待，t2再把t1唤醒(线程的启动先后顺序不由代码顺序决定，他们是并发的)
        t1.start();
        t2.start();

        //如果先开启t2通知线程，再开启t1等待线程，可能会出现t1线程等待没有收到通知的情况(线程的启动先后顺序不由代码顺序决定，他们是并发的)
//        t2.start();
//        t1.start();

        //实际上，调用start()方法就是告诉线程调度器，当前线程准备就绪，线程调度器在什么时候开启这个线程不确定，即调用start()方法开启线程的顺序，并不一定就是线程实际开启的顺序
        //在当前示例中，t1等待后让t2线程唤醒，如果t2线程先唤醒了，就不让t1线程等待了
    }
}

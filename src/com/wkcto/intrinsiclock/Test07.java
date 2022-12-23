package com.wkcto.intrinsiclock;

/**
 * 同步方法与同步代码块如何选择
 * 同步方法锁的粒度粗，执行效率低
 * 同步代码块，执行效率高一些
 */
public class Test07 {
    public static void main(String[] args) {
        Test07 test07 = new Test07();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test07.doLongTimeTask();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test07.doLongTimeTask();
            }
        }).start();
    }



    //同步方法，执行效率低
    public synchronized void doLongTimeTask() {
        try {
            System.out.println("Task Begin");
            Thread.sleep(3000);

            System.out.println("Starting synchronizing");
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            }


            System.out.println("Task end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    //同步代码块，锁的粒度细，执行效率高
    public void doLongTimeTask2() {
        try {
            System.out.println("Task Begin");
            Thread.sleep(3000);

            synchronized (this) {
                System.out.println("Starting synchronizing");
                for (int i = 1; i <= 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "-->" + i);
                }
            }

            System.out.println("Task end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

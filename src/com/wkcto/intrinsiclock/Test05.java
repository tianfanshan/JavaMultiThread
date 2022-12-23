package com.wkcto.intrinsiclock;

/**
 * synchronized同步实例方法
 * 把整个方法体作为同步代码块
 * 默认的锁对象是this对象
 */
public class Test05 {

    public static void main(String[] args) {
        //先创建Test01对象，通过对象名调用method()方法
        Test05 obj = new Test05();

        //一个线程调用method()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.method();//使用的锁对象this就是obj对象
            }
        }).start();

        //另一个线程调用method2()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.method2();//使用的锁对象this也是obj对象,可以同步
//                new Test05().method2();//使用的锁对象this是刚刚new创建的一个新对象，不是同一个锁对象，不能同步
            }
        }).start();

    }

    //定义方法，打印100行字符串
    public void method() {
        synchronized (this) {//经常使用this当前对象作为锁对象
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }

    //使用synchronized修饰实例方法，同步实例方法，默认this作为锁对象
    public synchronized void method2() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + " --> " + i);
        }
    }
}

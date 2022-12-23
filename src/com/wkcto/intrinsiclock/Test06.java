package com.wkcto.intrinsiclock;

/**
 * synchronized同步静态方法
 * 把整个方法体作为同步代码块
 * 默认的锁对象是当前类的运行时类对象,Test06.class,有人称它为类锁
 */
public class Test06 {

    public static void main(String[] args) {
        //先创建Test01对象，通过对象名调用method()方法
        Test06 obj = new Test06();

        //一个线程调用method1()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.method1();//使用的锁对象是Test06.class
            }
        }).start();

        //另一个线程调用method3()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                Test06.method3();
            }
        }).start();

    }

    //定义方法，打印100行字符串
    public void method1() {
        //使用当前类的运行时类对象作为锁对象，可以简单地理解为把Test06类的字节码文件作为锁对象
        synchronized (Test06.class) {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }

    //使用synchronized修饰静态方法，同步静态方法，默认当前运行时类Test06.class作为锁对象
    public synchronized static void method3() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + " --> " + i);
        }
    }
}

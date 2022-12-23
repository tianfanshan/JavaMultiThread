package com.wkcto.intrinsiclock;

/**
 * synchronized同步代码块
 * this锁对象
 */
public class Test01 {

    public static void main(String[] args) {
        //创建两个线程，分别调用method()方法
        //先创建Test01对象，通过对象名调用method()方法
        Test01 obj = new Test01();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.method();//使用的锁对象this就是obj对象
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.method();//使用的锁对象this也是obj对象
            }
        }).start();

    }

    //定义方法，打印100行字符串
    public void method(){
        synchronized (this){//经常使用this当前对象作为锁对象
            for (int i = 1; i <= 100; i++){
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }
}

package com.wkcto.intrinsiclock;

/**
 * synchronized同步代码块
 * 使用一个常量对象作为锁对象
 */
public class Test04 {

    public static void main(String[] args) {
        //创建两个线程，分别调用method()方法
        //先创建Test01对象，通过对象名调用method()方法
        Test04 obj = new Test04();
        Test04 obj2 = new Test04();

        new Thread(new Runnable() {
            @Override
            public void run() {

                obj.method();//使用的锁对象是OBJ常量
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj2.method();//使用的锁对象也是OBJ常量
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sMethod();//使用的锁对象也是OBJ常量
            }
        }).start();

    }

    public static final Object OBJ = new Object();//定义一个常量
    //定义方法，打印100行字符串
    public void method(){
        synchronized (OBJ){//使用一个常量对象作为锁对象
            for (int i = 1; i <= 100; i++){
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }

    public static void sMethod(){
        synchronized (OBJ){//使用一个常量对象作为锁对象
            for (int i = 1; i <= 100; i++){
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }
}

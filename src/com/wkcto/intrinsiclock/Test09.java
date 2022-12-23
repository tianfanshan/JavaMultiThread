package com.wkcto.intrinsiclock;

/**
 * 同步过程中线程出现异常,会自动释放锁对象
 */
public class Test09 {

    public static void main(String[] args) {
        //先创建Test01对象，通过对象名调用method()方法
        Test09 obj = new Test09();

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
                Test09.method3();
            }
        }).start();

    }

    //定义方法，打印100行字符串
    public void method1() {
        //使用当前类的运行时类对象作为锁对象，可以简单地理解为把Test06类的字节码文件作为锁对象
        synchronized (Test09.class) {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
                if (i == 50){
                    Integer.parseInt("abc");//将不符合格式的字符串转换为int将会出现异常
                }
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

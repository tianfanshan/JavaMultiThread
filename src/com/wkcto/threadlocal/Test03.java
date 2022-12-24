package com.wkcto.threadlocal;

import java.util.Date;
import java.util.Random;

/**
 * 定义ThreadLocal类的子类，在子类中重写initialValue()方法指定初始值，在第一次调用get()方法就不会返回null
 * ThreadLocal初始值
 */
public class Test03 {

    //1)定义ThreadLocal的自类
    static class SubThreadLocal extends ThreadLocal<Date>{
        //重写initialValue方法，设置初始值
        @Override
        protected Date initialValue() {
//            return new Date();//把当前日期设置会初始值
            return new Date(System.currentTimeMillis() - 1000 * 60 * 15);
        }
    }

    //定义ThreadLocal对象
//    static ThreadLocal threadLocal = new ThreadLocal();

    //直接使用自定义的SubThreadLocal对象
    static SubThreadLocal threadLocal = new SubThreadLocal();

    //定义线程类
    static class SubThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                //第一次调用threadLocal的get()方法会返回null
                System.out.println("---------" + Thread.currentThread().getName() + " value = " + threadLocal.get());
                //如果没有初始值就设置当前日期
                if (threadLocal.get() == null){
                    System.out.println("******************");
                    threadLocal.set(new Date());
                }
                try {
                    Thread.sleep(new Random().nextInt(500));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {

        SubThread t1 = new SubThread();
        t1.start();
        SubThread t2 = new SubThread();
        t2.start();
    }
}

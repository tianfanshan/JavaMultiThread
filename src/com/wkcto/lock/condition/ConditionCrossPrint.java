package com.wkcto.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition实现生产者/消费者设计模式中，两个线程交替打印
 */
public class ConditionCrossPrint {

    static class MyService{
        private Lock lock = new ReentrantLock();//创建锁对象
        private Condition condition = lock.newCondition();//创建Condition对象
        private boolean flag = true;//定义交替打印标志

        //定义方法只打印----横线
        public void printOne(){
            try {
                lock.lock();//锁定
                while (flag){//当flag为true等待
                    System.out.println(Thread.currentThread().getName() + " waiting....");
                    condition.await();
                }
                //flag为false时打印
                System.out.println(Thread.currentThread().getName() + " --------- ");
                flag = true;//修改交替打印标志
                condition.signal();//通知另外的线程打印
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();//释放锁对象
            }
        }


        //定义方法只打印*****横线
        public void printTwo(){
            try {
                lock.lock();//锁定
                while (!flag){//当flag为false等待
                    System.out.println(Thread.currentThread().getName() + " waiting....");
                    condition.await();
                }
                //flag为true时打印
                System.out.println(Thread.currentThread().getName() + " ********* ");
                flag = false;//修改交替打印标志
                condition.signal();//通知另外的线程打印
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();//释放锁对象
            }
        }



    }



    public static void main(String[] args) {
        MyService myService = new MyService();
        //创建一个线程打印-----
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService.printOne();
            }
        }).start();

        //创建线程打印*****
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService.printTwo();
            }
        }).start();
    }
}

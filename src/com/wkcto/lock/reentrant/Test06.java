package com.wkcto.lock.reentrant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 通过ReentrantLock锁的lockInterruptibly()方法避免死锁的产生
 */
public class Test06 {

    static class IntLock implements Runnable {
        //创建两个ReentrantLock锁对象
        public static ReentrantLock lock1 = new ReentrantLock();
//        public static Lock lock1 = new ReentrantLock();
        public static ReentrantLock lock2 = new ReentrantLock();
        int lockNum;//定义整数变量，决定使用那个锁

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            try {
                if (lockNum % 2 == 1) {//奇数,先锁1，再锁2
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁1，还需要获得锁2");
                    Thread.sleep(new Random().nextInt(500));
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得了锁1和锁2");
                } else {//偶数，先锁2，再锁1
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁2，还需要获得锁1");
                    Thread.sleep(new Random().nextInt(500));
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得了锁1和2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()){//这是ReentrantLock类中独有的方法,看15和16行,用于判断当前线程是否持有该锁
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "线程退出");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(22);

        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);

        t1.start();
        t2.start();


        //一下代码在实地开发当中不可用
        //再main线程，等待3秒，如果还有线程没有结束就中断该线程
        Thread.sleep(3000);

        //可以中断任何一个线程来解决死锁
        //当前线程会放弃对锁2的申请，同时释放锁1，t2线程会完成他的任务
        if (t1.isAlive()) {
            t1.interrupt();
        }
    }
}

package com.wkcto.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition等待与通知
 */
public class ConditionAwaitAndSignal {
    //定义锁
    private static final Lock LOCK = new ReentrantLock();
    private static final Condition CONDITION = LOCK.newCondition();

    //定义线程子类
    static class SubThread extends Thread {
        @Override
        public void run() {
            try {
                LOCK.lock();//在调用await()前必须先获得锁
                System.out.println("method lock");
                CONDITION.await();//等待
                System.out.println("method await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();//释放锁
                System.out.println("method unlock");

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread t1 = new SubThread();
        t1.start();


        Thread.sleep(1000);
        //主线程在水面三秒后，唤醒子线程的等待
        //主线程必须先持有该锁才能唤醒
        try {
            LOCK.lock();
            CONDITION.signal();
        } finally {
            LOCK.unlock();
        }
    }
}

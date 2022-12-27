package com.wkcto.lock.method;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * boolean hasWaiters(Condition condition) 查询是否有现成正在等待指定以Condition调教
 */
public class HasWaiters {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    static void sm(){
        try {
            lock.lock();
            System.out.println("是否有线程正在等待当前Condition条件: " + lock.hasWaiters(condition) + " -- waitQueueLength: " + lock.getWaitQueueLength(condition));
            System.out.println(Thread.currentThread().getName() + " waiting...");
            condition.await(new Random().nextInt(1000), TimeUnit.MILLISECONDS);//超时后会自动唤醒
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName() + "超时唤醒，是否有线程正在等待当前Condition条件: " + lock.hasWaiters(condition) + "-- waitQueueLength: " + lock.getWaitQueueLength(condition));
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                HasWaiters.sm();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
    }
}

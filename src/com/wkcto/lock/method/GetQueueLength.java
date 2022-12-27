package com.wkcto.lock.method;

import java.util.concurrent.locks.ReentrantLock;

/**
 * int getQueueLength()返回等待获得锁的线程预估数
 */
public class GetQueueLength {

    static ReentrantLock lock = new ReentrantLock();

    public static void sm(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁，执行方法，估计等待获得锁的线程数：" + lock.getQueueLength());
            Thread.sleep(1000);//睡眠一秒，模拟执行时间
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                GetQueueLength.sm();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
    }


}

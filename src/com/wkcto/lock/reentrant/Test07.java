package com.wkcto.lock.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock(long time, TimeUnit unit)的基本使用
 */
public class Test07 {

    static class TimeLock implements Runnable {
        private static ReentrantLock lock = new ReentrantLock();//定义锁对象

        @Override
        public void run() {
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)) {//获得锁返回true
                    System.out.println(Thread.currentThread().getName() + "获得锁，执行耗时任务");
                    Thread.sleep(2000);//如果这里睡眠的时间短于tryLock中设定的时间，则下个同样使用这段代码的线程可以继续执行，因为他在tryLock中等待的时间长于sleep的时间
//                    Thread.sleep(4000);//如果等待的时间大于tryLock方法里的时间，下个同样使用这个代码的线程就不再等待了(会放弃执行)
                } else {//没有获得锁
                    System.out.println(Thread.currentThread().getName() + "没有获得锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }


        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();

        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);

        t1.start();
        t2.start();
    }
}

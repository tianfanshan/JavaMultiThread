package com.wkcto.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用tryLock()避免死锁
 */
public class UseTryLockToAvoidDeadLock {
    static class IntLock implements Runnable {
        private ReentrantLock lock1 = new ReentrantLock();
        private ReentrantLock lock2 = new ReentrantLock();
        private int lockNum;

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            if (lockNum % 2 == 0) {//偶数先锁1，再锁2
                while (true) {
                    try {
                        if (lock1.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "获得锁1，还想获得锁2");
                            Thread.sleep(1000);
                            try {
                                if (lock2.tryLock()) {
                                    System.out.println(Thread.currentThread().getName() + "同时获得锁1和锁2 ---完成任务");
                                    return;//结束run()方法执行，即当前线程结束
                                }
                            } finally {
                                if (lock2.isHeldByCurrentThread()) {
                                    lock2.unlock();
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock1.isHeldByCurrentThread()) {
                            lock1.unlock();
                        }
                    }
                }
            }else {
                while (true) {
                    try {
                        if (lock2.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "获得锁2，还想获得锁1");
                            Thread.sleep(1000);
                            try {
                                if (lock1.tryLock()) {
                                    System.out.println(Thread.currentThread().getName() + "同时获得锁1和锁2 ---完成任务");
                                    return;//结束run()方法执行，即当前线程结束
                                }
                            } finally {
                                if (lock1.isHeldByCurrentThread()) {
                                    lock1.unlock();
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock2.isHeldByCurrentThread()) {
                            lock2.unlock();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(22);

        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);

        t1.start();
        t2.start();
        //运行后，使用tryLock()尝试获得锁，不会傻傻的等待，通过循环不行的再次尝试，如果等待的时间足够长，线程总是会获得想要的资源
    }
}

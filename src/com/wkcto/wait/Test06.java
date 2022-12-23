package com.wkcto.wait;

/**
 * notifyAll()与notify()
 */
public class Test06 {
    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();//定义一个对象作为子线程的锁对象
        SubThread t1 = new SubThread(lock);
        SubThread t2 = new SubThread(lock);
        SubThread t3 = new SubThread(lock);

        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);
        //调用notify()唤醒子线程
        synchronized (lock){
//            lock.notify();//随机唤醒一个线程，其他等待的线程依然处于等待状态，对于处于等待状态的县城来说，错过了通知信号,这种现象也成为信号丢失
            lock.notifyAll();//唤醒所有线程
        }
    }

    static class SubThread extends Thread{
        private Object lock;//定义实例变量作为锁对象

        public SubThread(Object lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + " -- begin wait...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " -- end wait...");
            }
        }
    }
}

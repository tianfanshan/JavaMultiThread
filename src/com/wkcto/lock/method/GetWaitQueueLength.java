package com.wkcto.lock.method;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * int getWaitQueueLength(Condition condition) 放回在Condition条件上等待的线程预估数
 */
public class GetWaitQueueLength {
    static class Service{
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void waitMethod(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 进入等待前，现在该condition条件上等待的线程预估数： " + lock.getWaitQueueLength(condition));
                condition.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void notifyMethod(){
            try {
                lock.lock();
                condition.signalAll();
                System.out.println("唤醒所有的等待后condition条件上等待的线程预估数：" + lock.getWaitQueueLength(condition));
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };

        //开启10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }

        Thread.sleep(1000);
        service.notifyMethod();
    }

}

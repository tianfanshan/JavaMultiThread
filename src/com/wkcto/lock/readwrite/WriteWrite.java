package com.wkcto.lock.readwrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示ReadWriteLock的writeLock()写锁是互斥的，只允许有一个线程持有
 */
public class WriteWrite {

    static class Service{
        //先定义读写锁
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        //定义方法修改数据
        public void write(){
            try {
                readWriteLock.writeLock().lock();//获得写锁
                System.out.println(Thread.currentThread().getName() + "获得写锁，开始修改数据的时间--" + System.currentTimeMillis());
                Thread.sleep(3000);//模拟修改数据的用时
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + "写入数据完毕时的时间--" + System.currentTimeMillis());
                readWriteLock.writeLock().unlock();//释放写锁
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.write();//调用修改数据的方法
                }
            }).start();
        }

        //从执行结果来看，同一时间只有一个线程获得写锁
    }
}

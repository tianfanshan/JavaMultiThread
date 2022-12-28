package com.wkcto.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class CreateFixedThreadPool {
    public static void main(String[] args) {
        //创建有5个线程大小的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        //向线程池提交18个任务,这18个任务存储到线程池的阻塞队列中，线程池中这五个线程就从阻塞队列中取执行任务
        for (int i = 0; i < 18; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId() + " 编号的任务在执行任务，开始时间: " + System.currentTimeMillis());
                    try {
                        Thread.sleep(3000);//模拟任务执行时长
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }


    }
}

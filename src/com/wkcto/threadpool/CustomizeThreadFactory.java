package com.wkcto.threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 自定义线程工厂
 */
public class CustomizeThreadFactory {
    public static void main(String[] args) throws InterruptedException {

        //定义任务
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt(10);
                System.out.println(Thread.currentThread().getId() + "--" + System.currentTimeMillis() + "开始睡眠：" + num + "秒");
                try {
                    TimeUnit.SECONDS.sleep(num);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        //创建线程池，使用自定义线程工厂,采用默认的拒绝策略
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);//设置为守护线程，当主线程运行结束，线程池中的线程会自动推出
                System.out.println("创建了线程：" + t);
                return t;
            }
        });

        //提交五个任务,当给当前线程池提交的任务超过五个时，线程池默认抛出异常
        for (int i = 0; i < 5; i++) {
            executorService.submit(r);
        }

        //主线程睡眠
        Thread.sleep(10000);

        //主线程睡眠超时，主线程结束，线程池中的线程会自动推出


    }
}

package com.wkcto.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 监控线程池
 */
public class MonitorThreadPool {
    public static void main(String[] args) throws InterruptedException {
        //定义任务
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + " 编号 的线程开始执行：" + System.currentTimeMillis());
                try {
                    Thread.sleep(20000);//线程睡眠20秒，模拟任务执行时长
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

        //向线程池提交30个任务
        for (int i = 0; i < 30; i++) {
            threadPoolExecutor.submit(r);
            System.out.println("当前线程池核心线程数：" + threadPoolExecutor.getCorePoolSize() + ", 最大线程数" +
                    threadPoolExecutor.getMaximumPoolSize() + ", 当前线程池大小：" + threadPoolExecutor.getPoolSize() +
                    ",活动线程数：" + threadPoolExecutor.getActiveCount() +
                    ", 收到任务数：" + threadPoolExecutor.getTaskCount() + ", 完成任务数：" + threadPoolExecutor.getCompletedTaskCount() +
                    ",等待任务数：" + threadPoolExecutor.getQueue().size());
            TimeUnit.MILLISECONDS.sleep(500);
        }

        System.out.println("--------------------");
        while (threadPoolExecutor.getActiveCount() > 0) {
            System.out.println("当前线程池核心线程数：" + threadPoolExecutor.getCorePoolSize() + ", 最大线程数" +
                    threadPoolExecutor.getMaximumPoolSize() + ", 当前线程池大小：" + threadPoolExecutor.getPoolSize() +
                    ",活动线程数：" + threadPoolExecutor.getActiveCount() +
                    ", 收到任务数：" + threadPoolExecutor.getTaskCount() + ", 完成任务数：" + threadPoolExecutor.getCompletedTaskCount() +
                    ",等待任务数：" + threadPoolExecutor.getQueue().size());
            Thread.sleep(1000);
        }
    }
}

package com.wkcto.threadmethod.p5yield;

/**
 * 使用yield()方法，让出CPU资源
 */
public class SubThread6 extends Thread{
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <= 1000000; i++){
            sum += i;
            Thread.yield();//线程让步，放弃CPU执行权
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - begin));
    }
}

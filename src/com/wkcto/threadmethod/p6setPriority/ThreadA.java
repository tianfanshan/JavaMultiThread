package com.wkcto.threadmethod.p6setPriority;

public class ThreadA extends Thread{
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (long i = 0; i <= 10000000000L; i++){
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("thread A:" + (end - begin));
    }
}

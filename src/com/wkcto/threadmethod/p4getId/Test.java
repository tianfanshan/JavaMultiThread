package com.wkcto.threadmethod.p4getId;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ", id: " + Thread.currentThread().getId());
        //子线程的id
        //线程的id可能会被重复使用(线程1结束后他的id可能会被线程2使用)
        //重启JVM后，同一个线程的编号可能会不一样(不要使用线程编号来做判断！！！)
        for (int i = 0; i < 20; i++){
            new SubThread5().start();
            Thread.sleep(100);
        }
    }
}

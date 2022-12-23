package com.wkcto.threadmethod.p4getId;

/**
 * 获得当前线程的ID(id的产生的随机的，不可用于判断)
 */
public class SubThread5 extends Thread{
    @Override
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName()
            + ", id: " + this.getId());
    }
}

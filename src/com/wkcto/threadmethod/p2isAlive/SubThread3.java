package com.wkcto.threadmethod.p2isAlive;

/**
 * 线程是否正在运行(true or false)
 */
public class SubThread3 extends Thread{
    @Override
    public void run() {
        System.out.println("run方法，isAlive: " + this.isAlive());//运行状态，true
    }
}

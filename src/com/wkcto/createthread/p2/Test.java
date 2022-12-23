package com.wkcto.createthread.p2;

/**
 * 演示线程运行结果的随机性
 */
public class Test {
    public static void main(String[] args) {

        MySecondThread mySecondThread = new MySecondThread();
        mySecondThread.start(); //开启子线程

        //当前是main线程
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("main: " + i);
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time); //线程睡眠，单位是毫秒，1秒 = 1000ms
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

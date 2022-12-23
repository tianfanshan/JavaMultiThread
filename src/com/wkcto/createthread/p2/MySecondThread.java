package com.wkcto.createthread.p2;


public class MySecondThread extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("sub thread: " + i);
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time); //线程睡眠，单位是毫秒，1秒 = 1000ms
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

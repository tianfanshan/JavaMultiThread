package com.wkcto.wait;

/**
 * wait(long)
 */
public class Test07 {

    private final static  Object LOCK = new Object();
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK){
                    try {
                        System.out.println("thread begin wait...");
                        LOCK.wait(5000);//如果5000毫秒内没有被唤醒，会自动唤醒
                        System.out.println("end wait...");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        t.start();
    }


}

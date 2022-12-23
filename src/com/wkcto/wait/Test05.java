package com.wkcto.wait;

/**
 * Interrupt()会中断线程的wait()等待
 */
public class Test05 {

    private static final Object LOCK = new Object();//定义常量作为锁对象

    public static void main(String[] args) throws InterruptedException {
        SubThread t = new SubThread();
        t.start();

        Thread.sleep(2000);//主线程睡眠2秒，确保子线程处于wait等待状态
        t.interrupt();//经过使用方法interrupt()线程会在wait()方法之后进入到catch，不会执行wait()方法之后的代码，进入异常后会释放锁对象
    }

    static class SubThread extends Thread {
        @Override
        public void run() {
            synchronized (LOCK) {
                try {
                    System.out.println("being wait...");
                    LOCK.wait();
                    System.out.println("end wait...");
                } catch (InterruptedException e) {
                    System.out.println("wait等待被中断了");
                }

            }
        }
    }


}

package com.wkcto.wait;

/**
 * wait()会使线程等待
 * 需要放在同步代码块中，通过锁对象(列：以下代码中的text)调用
 */
public class Test02 {
    public static void main(String[] args) {
        try {
            String text = "wkcto";
            String another = "hello";
            System.out.println("同步前的代码");
            synchronized (text){
                System.out.println("同步代码块开始....");
                text.wait();//调用wait()方法后，当前线程就会等待，释放锁对象，当前线程需要被唤醒，如果没有唤醒就会一直等待
                another.wait();//不是锁对象调用会产生java.lang.IllegalMonitorStateException异常
                System.out.println("wait后面的代码....");
            }
            System.out.println("同步代码块后面的代码");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("main后面的其他代码...");
    }
}

package com.wkcto.threadsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示线程的原子性问题
 */
public class Test01 {
    public static void main(String[] args) {

        MyInt myInt = new MyInt();

        for (int i = 1; i <= 2; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.println(Thread.currentThread().getName() + " -> " + myInt.getNum());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();
        }
    }

    static class MyInt{
        AtomicInteger num = new AtomicInteger();//在Java中提供了一个线程安全的AtomicInteger类，保证了操作的原子性
        //int num;
        public int getNum(){
            return num.getAndIncrement();
            //return num++;
            /*
            num++
            自增操作实现的步骤：
                读取num的值
                num自增
                把自增后的值再赋值给num变量
             */
        }
    }


}

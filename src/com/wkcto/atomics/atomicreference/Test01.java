package com.wkcto.atomics.atomicreference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用AtomicReference原子读写一个对象
 */
public class Test01 {

    //船舰一个AtomicReference对象
    static AtomicReference<String> atomicReference = new AtomicReference<>("abc");

    public static void main(String[] args) {
        //创建100个线程修改字符串
        for (int i = 0; i < 100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (atomicReference.compareAndSet("abc","def")){
                        System.out.println(Thread.currentThread().getName() + "把字符串abc更改为def");
                    }
                }
            }).start();
        }

        //再创建100个线程
        for (int i = 0; i < 100; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (atomicReference.compareAndSet("def","abc")){
                        System.out.println(Thread.currentThread().getName() + "把字符串还原为abc");
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(atomicReference.get());

    }
}

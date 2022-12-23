package com.wkcto.wait;

import java.util.ArrayList;
import java.util.List;

public class Test04 {


    public static void main(String[] args) throws InterruptedException {
        //定义一个List集合存储String数据
        List<String> list = new ArrayList<>();

        //定义第一个线程，当list集合中元素的数量不等于5时线程等待
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    if (list.size() != 5) {
                        System.out.println("线程1开始等待： " + System.currentTimeMillis());
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("线程1被唤醒： " + System.currentTimeMillis());
                    }
                }
            }
        });

        //定义第二个线程,想list集合中添加元素
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    for (int i = 0; i < 10; i++) {
                        list.add("data--" + i);
                        System.out.println("线程2添加第" + (i + 1) + "个数据");
                        //判断元素的数量是否满足唤醒线程1
                        if (list.size() == 5) {
                            list.notify();//唤醒线程,但不会立即释放锁对象,需要等到当前同步代码块都执行完后才能释放锁对象！！！
                            System.out.println("线程2已经发出唤醒通知");
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        t1.start();
        //为了确保t2在t1之后开启，即让t1线程先睡眠
        Thread.sleep(500);
        t2.start();
    }
}

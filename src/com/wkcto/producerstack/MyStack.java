package com.wkcto.producerstack;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟栈
 */
public class MyStack {
    private List<Object> list = new ArrayList<>();//定义集合模拟栈
    private static final int MAX = 1;//集合的最大容量

    //定义方法模拟入栈
    public synchronized void push() {
        //当栈中的数据已满就等待
        while (list.size() >= MAX){
            System.out.println(Thread.currentThread().getName() + " begin wait...");
            try {
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        String data = "data--" + Math.random();
        System.out.println(Thread.currentThread().getName() + "添加了数据：" + data);
        list.add(data);
//        this.notify();//当多个生产者多个消费者时，使用notify()可能会出现假死的情况
        this.notifyAll();
    }

    //定义方法模拟出栈
    public synchronized void pop() {
        //如果没有数据就等待
        while (list.size() == 0){
            try {
                System.out.println(Thread.currentThread().getName() + " begin wait...");
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "出栈数据：" + list.remove(0));
//            this.notify();//当多个生产者多个消费者时，使用notify()可能会出现假死的情况
            this.notifyAll();
        }
    }
}

package com.wkcto.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * wait条件发生变化
 * 定义一个集合
 * 定义一个线程向集合中添加数据，添加完数据后通知另外的线程从集合中取数据
 * 定义一个线程从集合中取数据，如果集合中没有数据则等待
 */
public class Test10 {
    public static void main(String[] args) {

        //定义添加数据的线程对象啊ing
        ThreadAdd threadAdd = new ThreadAdd();

        //定义取数据的线程对象
        ThreadSubtract threadSubtract = new ThreadSubtract();
        threadSubtract.setName("subtract 1");

        //测试一：先开启添加数据的线程，在开启一个取数据的线程，大多数情况下会正常取数据(这些线程是并发的)
//        threadAdd.start();
//        threadSubtract.start();

        //测试二：先开启取数据的线程，再开启添加数据的线程，取数据的线程会先等待，等到添加数据之后，再取数据(这些线程是并发的)
//        threadSubtract.start();
//        threadAdd.start();

        //测试三：开启两个取数据的线程，再开启添加数据的线程
        ThreadSubtract threadSubtract2 = new ThreadSubtract();
        threadSubtract.setName("subtract 2");
        threadSubtract.start();
        threadSubtract2.start();
        threadAdd.start();
        /*
        某一次执行结果如下：
            subtract 2 begin wait...
            Thread-2从集合中去除了data后，集合中数据的数量: 0
            subtract 2 end wait...
            Exception in thread "subtract 2" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        分心可能的执行顺序：
            threadSubtract线程先启动，取数据时，集合中没有数据，wait()等待
            threadAdd线程获得CPU执行权，添加数据，把threadSubtract线程唤醒，
            threadSubtract2线程开启后获得CPU执行权，正常取数据
            threadSubtract线程获得CPU执行权，打印end wait...，然后再执行list.remove(index)取数据时，现在list集合中极影没有了数据，这是会产生java.lang.IndexOutOfBoundsException异常
        出现异常的原因是：
            向list集合中天就啊了一个数据，但是remove()了两次
            如何解决？
                当等待的线程被唤醒后，再判断一次集合中是否有数据可取，即需要把subtract()方法中的if判断改为while
         */

    }

    //1)定义List集合
    static List<Object> list = new ArrayList<>();

    //定义方法从集合中取数据
    public static void subtract(int index){
        synchronized (list){
//            if (list.size() == 0){
            while (list.size() == 0){
                try {
                    System.out.println(Thread.currentThread().getName() + " begin wait...");
                    list.wait();
                    System.out.println(Thread.currentThread().getName() + " end wait...");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            Object data = list.remove(index);
            System.out.println(Thread.currentThread().getName() + "从集合中去除了" + data + "后，集合中数据的数量: " + list.size());
        }
    }

    //3)定义方法向集合中添加数据后，通知等待的线程取数据
    public static void add(Object data){
        synchronized (list){
            list.add(data);
            System.out.println(Thread.currentThread().getName() + "存储了一个数据");
            list.notifyAll();
        }
    }

    //4)定义线程类调用add()取数据方法
    static class ThreadAdd extends Thread{
        @Override
        public void run() {
            add("data");
        }
    }

    //5)定义线程类调用subtract()方法
    static class ThreadSubtract extends Thread{
        @Override
        public void run() {
            subtract(0);
        }
    }
}

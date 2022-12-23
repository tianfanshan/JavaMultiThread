package com.wkcto.volatilekw;

/**
 * volatile不具备原子性
 */
public class Test03 {
    public static void main(String[] args) {
        //在main线程中创建100个子线程
        for (int i = 0; i < 100; i++){
            new MyThread().start();
        }
    }

    static class MyThread extends Thread {
        //volatile关键字仅仅是表示所有线程从主内存读取count变量的值
//        volatile public static int count;
        public static int count;

        //这段代码运行后不是线程安全的，想要实现线程安全，需要使用synchronized进行同步，如果使用synchronized同步，就不需要使用volatile关键字了
//        public static void addCount() {
//            for (int i = 0; i < 1000;i++){
//                //count++不是原子操作
//                count++;
//            }
//            System.out.println(Thread.currentThread().getName() + " count: " + count);
//        }


        //添加synchronized使addCount()拥有原子特性
        public synchronized static void addCount() {
            for (int i = 0; i < 1000;i++){
                //count++不是原子操作
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " count: " + count);
        }

        @Override
        public void run() {
            addCount();
        }
    }
}

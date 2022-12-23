package com.wkcto.volatilekw;

public class Volatile {
    public static void main(String[] args) {
        //创建PrintString对象
        PrintString printString = new PrintString();

        //开启子线程，让子线程执行printString对象的printStringMethod()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                printString.printStringMethod();
            }
        }).start();

        //main线程睡眠1000毫秒
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("在main线程中修改打印标志");
        printString.setContinuePrint(false);
        //程序运行，查看在main线程中修改了打印标志后，子线程打印是否可以结束打印
        //程序运行后，可能会出现死循环情况
        //分析原因： main线程修改了printString对象的打印标志后，子线程读不到
        //解决办法： 使用volatile关键字修饰printString对象的打印标志
        //      volatile的作用可以强制线程从公共内存中读取变脸的值，而不是从工作内存中读取

    }


    //定义类打印字符串
    static class PrintString {

        //添加volatile修饰，从公共内存中读取continuePrint，而不是从从工作内存中读取
        //1)volatile只能修饰变量，而synchronized可以修饰方法和代码块,随着JDK新版本的发布，synchronized的执行效率也有较大的提升
        //在开发当中使用synchronized的比率还是很大的
        //2)多线程访问volatile变量不会发生阻塞，二synchronized可能会阻塞
        //3)volatile能保证数据的可见性，但不能保证原子性,而synchronized可以保证原子性，也可以保证可见性
        //4)关键字volatile解决的时变量在多个线程之间的可见性,synchronized关键字解决多个线程之间访问公共资源的同步
        private volatile boolean continuePrint = true;
        //private boolean continuePrint = true;

        public void setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
        }

        public void printStringMethod() {
            System.out.println(Thread.currentThread().getName() + "开始");
            while (continuePrint){
//                System.out.println("sub thread....");
//                try {
//                    Thread.sleep(500);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }
    }
}

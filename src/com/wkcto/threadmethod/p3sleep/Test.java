package com.wkcto.threadmethod.p3sleep;

public class Test {
    public static void main(String[] args) {
        SubThread4 subThread4 = new SubThread4();
        System.out.println("main_begin = " + System.currentTimeMillis());
//        subThread4.start();//开启新的线程
        subThread4.run();//在main线程中调用实例方法run(),没有开启新的线程
        System.out.println("main_end = " + System.currentTimeMillis());
    }
}

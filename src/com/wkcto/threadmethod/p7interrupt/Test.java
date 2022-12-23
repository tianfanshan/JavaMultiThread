package com.wkcto.threadmethod.p7interrupt;

public class Test {
    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        subThread.start();//开启子线程

        //当前线程是main线程
        for (int i = 1; i<= 100 ; i++){
            System.out.println("main ==> " + i);
        }

        //终端子线程
        subThread.interrupt();//仅仅是给子线程标记中断，子线程没有真正的中断
    }
}

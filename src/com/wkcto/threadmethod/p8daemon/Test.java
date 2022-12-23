package com.wkcto.threadmethod.p8daemon;

public class Test {
    public static void main(String[] args) {

        SubDaemonThread thread = new SubDaemonThread();
        //设置线程为守护线程
        thread.setDaemon(true);//设置守护线程的代码应该在线程前启动
        thread.start();
//        thread.setDaemon(true);//这就没用了

        //当前线程为main线程
        for (int i = 1; i <= 10; i++) {
            System.out.println("main ==> " + i);
        }

        //当main线程(main线程并非是守护线程)结束，守护线程(SubDaemonThread)thread也销毁了
        //当程序中普通线程停止运行守护线程就会自动销毁
    }
}

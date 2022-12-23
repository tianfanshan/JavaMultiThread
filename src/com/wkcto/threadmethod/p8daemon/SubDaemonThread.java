package com.wkcto.threadmethod.p8daemon;

public class SubDaemonThread extends Thread{
    @Override
    public void run() {
        super.run();
        while (true){
            System.out.println("sub thread....");
        }
    }
}

package com.wkcto.threadmethod.p7interrupt;

public class SubThread extends Thread{
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10000; i++){
            System.out.println("sub thread --> " + i);
        }
    }
}

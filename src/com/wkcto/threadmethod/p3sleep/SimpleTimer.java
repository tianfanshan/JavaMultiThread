package com.wkcto.threadmethod.p3sleep;

/**
 * 使用线程休眠Thread.sleep完成一个简易的计时器
 */
public class SimpleTimer {
    public static void main(String[] args) {

        int remaining = 5;
        //读取main方法的参数
        if (args.length == 1){
            remaining = Integer.parseInt(args[0]);
        }

        while (remaining >= 0){
            System.out.println("Remaining " +remaining);
            remaining--;
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("Done");
    }
}

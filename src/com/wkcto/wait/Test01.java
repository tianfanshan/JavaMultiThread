package com.wkcto.wait;

/**
 * 显示wait()/notify()方法，需要放在同步代码块中,否则会产生java.lang.IllegalMonitorStateException
 * 任何对象都可以调用wait()/notify(),这两个方法是从Object类继承来的
 */
public class Test01 {
    public static void main(String[] args) {
        try {
            String test = "wkcto";
            test.wait();//java.lang.IllegalMonitorStateException异常
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

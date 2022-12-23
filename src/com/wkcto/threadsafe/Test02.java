package com.wkcto.threadsafe;

import java.util.Random;

/**
 * 测试线程的可见性
 */
public class Test02 {
    public static void main(String[] args) throws InterruptedException {

        MyTask myTask = new MyTask();
        new Thread(myTask).start();

        Thread.sleep(1000);
        //主线程一秒后取消子线程
        myTask.cancel();

        /*
            可能会出现以下情况：
                在main线程中调用了task.cancel()方法，把task对象的toCancel变量修改为true
                可能存在子线程看不到main线程对toCancel做的修改，在子线程中toCancel变量一直为false
            导致子线程看不到main线程对toCancel变量更新的原因,可能：
                1)JIT即时编译器可能 会对run方法中的while循环进行优化为：
                    如果这样就会导致死循环
                    if(!toCancel){
                        while(true){
                            if(doSomething()){
                            }
                        }
                    }
                2)可能与计算机的存储系统有关，假设分别由两个CPU内核运行main线程与子线程，
                运行子线程的CPU无法立即读取运行main线程CPU中的数据
         */
    }

    static class MyTask implements Runnable{

        private boolean toCancel = false;

        @Override
        public void run() {
            while (!toCancel){
                try {
                    if (doSomething()){
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (toCancel){
                System.out.println("任务被取消");
            }else {
                System.out.println("任务正常结束");
            }
        }

        private boolean doSomething() throws InterruptedException {
            System.out.println("执行某个任务....");
            Thread.sleep(new Random().nextInt(1000));//任务执行时间
            return true;
        }

        public void cancel(){
            toCancel = true;
            System.out.println("收到 取消线程的消息");
        }
    }
}

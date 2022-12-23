package com.wkcto.atomics.atomiclong;


import java.util.Random;

/**
 * 模拟服务器的请求总数，处理成功数，处理失败数
 */
public class Test {
    public static void main(String[] args) {
        //通过线程来模拟请求,在实际应用中可以在ServletFilter中调用Indicator计数器的相关方法
        for (int i = 0; i < 10000; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //每个线程就是一个请求，请求总数要加1
                    Indicator.getInstance().newRequestReceive();
                    int num = new Random().nextInt();
                    if (num % 2 == 0){//偶数模拟成功
                        Indicator.getInstance().requestProcessSuccess();
                    }else {//奇数模拟失败
                        Indicator.getInstance().requestProcessFailed();
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //打印结果
        System.out.println("总的请求数 " + Indicator.getInstance().getRequestCount());//总的请求数
        System.out.println("成功数 " + Indicator.getInstance().getSuccessCount());//成功数
        System.out.println("失败数： " + Indicator.getInstance().getFailedCount());//失败数
    }
}

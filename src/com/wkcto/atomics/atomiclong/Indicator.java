package com.wkcto.atomics.atomiclong;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用原子变量类定义一个计数器
 * 该计数器，在整个程序中都能使用，并且所有的地方都使用者一个计数器，这个计数器就可以设计为单例
 */
public class Indicator {
    //构造方法私有化
    private Indicator(){

    }

    //定义一个私有的本类静态对象
    private static final Indicator INSTANCE = new Indicator();

    //提供一个公共静态方法返回该类唯一实例
    public static Indicator getInstance(){
        return INSTANCE;
    }


    //使用原子变量类保存请求总数，成功数，失败数
    private final AtomicLong requestCount = new AtomicLong(0);//记录请求总数
    private final AtomicLong successCount = new AtomicLong(0);//处理成功总数
    private final AtomicLong failedCount = new AtomicLong(0);//处理失败总数



    //有新的请求
    public void newRequestReceive(){
        requestCount.incrementAndGet();
    }
    //处理成功
    public void requestProcessSuccess(){
        successCount.incrementAndGet();
    }
    //处理失败
    public void requestProcessFailed(){
        failedCount.incrementAndGet();
    }



    //查看总数
    public long getRequestCount(){
        return requestCount.get();
    }
    //查看成功数
    public long getSuccessCount(){
        return successCount.get();
    }
    //查看失败数
    public long getFailedCount(){
        return failedCount.get();
    }
}

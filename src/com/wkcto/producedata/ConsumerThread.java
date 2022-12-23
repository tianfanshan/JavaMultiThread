package com.wkcto.producedata;

/**
 * 定义线程类模拟消费者
 */
public class ConsumerThread extends Thread{
    //消费者使用数据，就是使用ValueOP类的value字段值
    private ValueOP valueOP;

    public ConsumerThread(ValueOP valueOP) {
        this.valueOP = valueOP;
    }

    @Override
    public void run() {
        while (true){
            valueOP.getValue();
        }
    }
}

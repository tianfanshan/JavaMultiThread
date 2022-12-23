package com.wkcto.producedata;

/**
 * 定义线程类模拟生产者
 */
public class ProducerThread extends Thread{
    //生产者生产数据就是调用ValueOP类的setValue方法给value字段赋值
    private ValueOP valueOP;

    public ProducerThread(ValueOP valueOP) {
        this.valueOP = valueOP;
    }

    @Override
    public void run() {
        while (true){
            valueOP.setValue();
        }
    }
}

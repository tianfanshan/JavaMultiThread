package com.wkcto.producedata;

/**
 * 测试一生产，一消费的情况
 */
public class Test {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();
        ProducerThread producerThread = new ProducerThread(valueOP);
        ConsumerThread consumerThread = new ConsumerThread(valueOP);

        producerThread.start();
        consumerThread.start();
        //生产与消费交替运行
    }
}

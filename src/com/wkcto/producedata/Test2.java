package com.wkcto.producedata;

/**
 * 测试多生产，多消费的情况
 */
public class Test2 {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();
        ProducerThread producerThread1 = new ProducerThread(valueOP);
        ProducerThread producerThread2 = new ProducerThread(valueOP);
        ProducerThread producerThread3 = new ProducerThread(valueOP);
        ConsumerThread consumerThread1 = new ConsumerThread(valueOP);
        ConsumerThread consumerThread2 = new ConsumerThread(valueOP);
        ConsumerThread consumerThread3 = new ConsumerThread(valueOP);

        producerThread1.start();
        producerThread2.start();
        producerThread3.start();
        consumerThread1.start();
        consumerThread2.start();
        consumerThread3.start();
        //生产与消费交替运行
    }
}

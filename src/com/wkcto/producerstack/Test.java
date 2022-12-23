package com.wkcto.producerstack;

/**
 * 测试一生产一消费的情况
 */
public class Test {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        ProducerThread p = new ProducerThread(stack);
        ConsumerThread c = new ConsumerThread(stack);

        p.start();
        c.start();

        /*
            运行结果是两个线程交替执行，一个线程负责生产，通知另外一个线程负责消费
         */
    }
}

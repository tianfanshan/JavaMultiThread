package com.wkcto.producerstack;

/**
 * 测试一生产多消费的情况
 */
public class Test02 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        ProducerThread p1 = new ProducerThread(stack);
        ProducerThread p2 = new ProducerThread(stack);
        ProducerThread p3 = new ProducerThread(stack);
        ConsumerThread c1 = new ConsumerThread(stack);
        ConsumerThread c2 = new ConsumerThread(stack);
        ConsumerThread c3 = new ConsumerThread(stack);

        p1.setName("生产者1号");
        p1.setName("生产者2号");
        p1.setName("生产者3号");
        c1.setName("消费者1号");
        c1.setName("消费者2号");
        c1.setName("消费者3号");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();

        /*
            运行结果是两个线程交替执行，一个线程负责生产，通知另外一个线程负责消费
         */
    }
}

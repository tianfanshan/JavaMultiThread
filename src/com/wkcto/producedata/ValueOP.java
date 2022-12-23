package com.wkcto.producedata;

/**
 * 定义一个操作数组的类
 */
public class ValueOP {
    private String value = "";

    //定义方法修改value字段的值
    public void setValue() {
        synchronized (this) {
            //如果value值不是""空串就等待
//            if (!value.equalsIgnoreCase("")) {
            while (!value.equalsIgnoreCase("")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果value字段值时空船，设置value字段的值
            String value = System.currentTimeMillis() + " - " + System.nanoTime();
            System.out.println("set设置的值时：" + value);
            this.value = value;
//            this.notify();//再多生产者多消费者环境中，notify()不能保证是生产者唤醒消费者，如果生产者唤醒的还是生产者可能会出现假死的情况
            this.notifyAll();
        }
    }

    //定义方法读取字段值
    public void getValue() {
        synchronized (this) {
            //如果value时空串就等待
//            if (value.equalsIgnoreCase("")) {
            while (value.equalsIgnoreCase("")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //怒视空串，读取字段值
            System.out.println("get的值是：" + this.value);
            this.value = "";
//            this.notify();
            this.notifyAll();
        }
    }
}

package com.wkcto.producerstack;

/**
 * 生产者线程
 */
public class ConsumerThread extends Thread{

    private MyStack myStack;

    public ConsumerThread(MyStack myStack) {
        this.myStack = myStack;
    }

    @Override
    public void run() {
        while (true){
            myStack.pop();
        }
    }
}

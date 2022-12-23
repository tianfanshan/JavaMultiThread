package com.wkcto.producerstack;

/**
 * 生产者线程
 */
public class ProducerThread extends Thread{

    private MyStack myStack;

    public ProducerThread(MyStack myStack) {
        this.myStack = myStack;
    }

    @Override
    public void run() {
        while (true){
            myStack.push();
        }
    }
}

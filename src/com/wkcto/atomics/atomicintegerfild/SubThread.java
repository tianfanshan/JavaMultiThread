package com.wkcto.atomics.atomicintegerfild;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 线程类
 */
public class SubThread extends Thread{
    private User user;//要更新的User对象

    //创建AtomicIntegerFiledUpdater更新器,更新User对象中age字段(age字段有volatile修饰符)
    private AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    public SubThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        //再子线程中对user对象的age字段自增10次
        for (int i = 0 ;i < 10; i++){
            System.out.println(updater.getAndIncrement(user));
        }
    }
}

package com.wkcto.atomics.atomicintegerfild;

/**
 * 使用AtomicIntegerFieldUpdater更新字段必须使用volatile修饰符
 */
public class User {
    int id;

    //添加volatile修饰，使这个字段可以被AtomicIntegerFiledUpdater更新 -> SubThread
    volatile int age;

    public User(int id, int age) {
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}

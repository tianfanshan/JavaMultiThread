package com.wkcto.atomics.atomicintegerfild;

/**
 * 使用AtomicIntegerFiledUpdater更新的字段必须使用volatile修饰
 */
public class Test {
    public static void main(String[] args) {
        User user = new User(1234,10);
        //开启10个线程
        for (int i = 0; i< 10; i++){
            new SubThread(user).start();
        }
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(user);
    }

}

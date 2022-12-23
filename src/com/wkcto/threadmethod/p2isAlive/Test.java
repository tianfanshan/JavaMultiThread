package com.wkcto.threadmethod.p2isAlive;

public class Test {
    public static void main(String[] args) {
        SubThread3 subThread3 = new SubThread3();
        System.out.println("Begin==" + subThread3.isAlive());//false，在启动线程之前肯定是false
        subThread3.start();
        while (subThread3.isAlive()){
            System.out.println("end==true");//这个结果不确定，打印这一行时，如果subThread3线程还未结束则返回true，如果已结束则返回false
        }
        System.out.println("end==false");
    }
}

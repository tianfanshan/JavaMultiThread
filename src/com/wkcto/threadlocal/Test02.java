package com.wkcto.threadlocal;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 在多线程环境中，把字符串转换为日期对象，多个线程使用同一个SimpleDateFormat对象可能会产生线程安全问题，有异常
 * 为每个线程制定自己的SimpleDateFormat对象，使用ThreadLocal
 */
public class Test02 {
    //定义SimpleDateFormat对象，该对象可以把字符串转换为日期
//    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal();

    //定义Runnable接口实现类
    static class ParseDate implements Runnable {
        private int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                String text = "2068-11-22 08:28:" + i % 60;//构建日期字符串
//                Date date = sdf.parse(text);//把字符串转换为日期

                //先判断当前线程是否有SimpleDateFormat对象，如果当前线程没有SimpleDateFormat对象就创建一个，如果有就直接使用
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));//使用ThreadLocal为每个线程提供他们自己的SimpleDateFormat这样就不会出现线程安全问题
                }
                Date date = threadLocal.get().parse(text);
                System.out.println(i + " -- " + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //创建100个线程
        for (int i = 0; i < 100; i++) {
            new Thread(new ParseDate(i)).start();
        }
    }
}

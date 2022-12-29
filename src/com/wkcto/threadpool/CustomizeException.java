package com.wkcto.threadpool;

import java.util.concurrent.*;

/**
 * 自定义线程池类，对ThreadPoolExecutor进行扩展
 */
public class CustomizeException {

    //自定义线程池
    private static class TraceThreadPoolExecutor extends ThreadPoolExecutor{
        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }
        //定义方法，对执行的任务进行包装，接收两个参数，第一个参数接收要执行的任务，第二个参数是一个Exception异常
        public Runnable wrap(Runnable task, Exception exception){
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    }catch (Exception e){
                        exception.printStackTrace();
                        throw e;
                    }
                }
            };
        }

        //重写submit方法

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task,new Exception("客户跟踪异常")));
        }

        @Override
        public void execute(Runnable command) {
            super.execute(wrap(command,new Exception("客户跟踪异常")));
        }
    }

    private static class DivideTask implements Runnable{
        private int x;
        private int y;

        public DivideTask(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "计算：" + x + " / " + y + " = " + (x/y));
        }
    }
    public static void main(String[] args) {
        //创建线程池
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0, TimeUnit.SECONDS,new SynchronousQueue<>());

        //使用自定义的线程池
        ThreadPoolExecutor poolExecutor = new TraceThreadPoolExecutor(0,Integer.MAX_VALUE,0, TimeUnit.SECONDS,new SynchronousQueue<>());

        //向线程池中添加计算两个数相除的任务
        for (int i = 0; i < 5; i++) {
            poolExecutor.submit(new DivideTask(10,i));
//            poolExecutor.execute(new DivideTask(10,i));
        }
    }
}

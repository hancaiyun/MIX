package com.nicehancy.MIX.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *     FixedThreadPool线程池测试
 *     最大线程数即为核心线程数， 未超过核心线程数， 开启线程， 超过则加入到无界队列LinkedBlockingQueue中
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/31 15:35
 **/
public class NewFixedThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService pool= Executors.newFixedThreadPool(3);

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口;
        Thread t1=new MyThread();
        Thread t2=new MyThread();
        Thread t3=new MyThread();
        Thread t4=new MyThread();
        Thread t5=new MyThread();

        //将线程放到池中执行；
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        //关闭线程池
        pool.shutdown();
    }
}

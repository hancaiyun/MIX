package com.nicehancy.MIX.threadPool;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/31 15:32
 **/
public class MyThread extends Thread{

    @Override
    public void run(){

        System.out.println(Thread.currentThread().getName() + "正在执行...");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }
}

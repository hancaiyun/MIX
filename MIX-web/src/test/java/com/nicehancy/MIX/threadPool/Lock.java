package com.nicehancy.MIX.threadPool;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/2 11:09
 **/
public class Lock {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        new Thread(new XMan(o1, o2), "A").start();
        new Thread(new XMan(o1, o2), "B").start();

        new Thread(new YMan(), "Yman-01").start();
        System.out.println("main....end....");
    }
}


class XMan implements Runnable{
    private final Object o1;
    private final Object o2;

    public XMan(Object o1,Object o2){
        this.o1 = o1;
        this.o2 = o2;
    }


    @Override
    public void run() {
        synchronized (o1){

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2){
                System.out.println(Thread.currentThread().getName()+"==>ok");
            }
        }
    }
}


class YMan implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            try{TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e){}
            System.out.println(Thread.currentThread().getName()+" is running "+i);
        }
    }
}
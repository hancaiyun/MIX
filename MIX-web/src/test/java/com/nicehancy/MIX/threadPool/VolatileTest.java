package com.nicehancy.MIX.threadPool;

/**
 * 在主线程将子线程实例的flag置为false后,子线程中的flag竟然还是true
 * 可见性问题
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/2 14:18
 **/
public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {

        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();
        //Thread.sleep(1000);
        threadDemo.flag = false;
        System.out.println("已将flag置为" + threadDemo.flag);

    }

    static class ThreadDemo implements Runnable {
        //volatile
        boolean flag = true;
        @Override
        public void run() {
            System.out.println("Flag=" + flag);
        }

    }
}

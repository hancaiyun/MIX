package com.nicehancy.MIX.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * <p>
 *     13个人5个窗口购票模拟
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/31 11:22
 **/
public class Ticket {

    /**
     * 购票流程
     * @param args
     */
    public static void main(String[] args) {
        Semaphore windows = new Semaphore(5);// 声明5个窗口

        //模拟13个人排队，总共5个窗口购票
        for (int i = 0; i < 13; i++) {
            new Thread(() -> {
                try {
                    windows.acquire();//占用窗口
                    System.out.println(Thread.currentThread().getName() + ": 开始买票");
                    Thread.sleep(2000);//睡2秒，模拟买票流程
                    System.out.println(Thread.currentThread().getName() + ": 购票成功");
                    windows.release();//释放窗口
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

package com.nicehancy.MIX;

import com.nicehancy.MIX.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * <p>
 *     信号量并发管理
 *     Semaphore用于管理信号量，在并发编程中，可以控制返访问同步代码的线程数量
 * <p/>
 *
 * @author hancaiyun
 * @since 2019/12/31 11:14
 **/
@Slf4j
public class SemaphoreTest extends BaseSpringTest {

    /**
     * 窗口购票模拟
     */
    @Test
    public void ticketTest(){
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

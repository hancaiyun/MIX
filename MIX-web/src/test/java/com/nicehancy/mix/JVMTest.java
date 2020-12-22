package com.nicehancy.mix;

import com.nicehancy.mix.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/12/4 17:08
 **/
@Slf4j
public class JVMTest extends BaseSpringTest {

    @Test
    public void test(){
        Worker worker = new Worker();
        while (true) {
            worker.useWorker();
        }
    }

    static class Worker {

        public Worker worker;

        public Worker getWorker() {
            return null == worker ? new Worker() : worker;  //对象定义在方法体内部。
        }

        public void setWorker() {
            worker = new Worker();
        }


        public void useWorker() {
            Worker obj = getWorker();
        }

        public void useWorker2() {
            Worker obj = getWorker(); //没有被外部使用，不为内存逃逸
        }
    }
}

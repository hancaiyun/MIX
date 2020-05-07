package com.nicehancy.mix.api;

import com.nicehancy.mix.base.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * 系统信息查询接口测试
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/14 14:02
 **/
@Slf4j
public class SystemServiceTest extends BaseSpringTest {

    @Test
    public void test(){
        Runtime runtime = Runtime.getRuntime();
        //可以获得最大内存
        long maxMemory = runtime.maxMemory()/(1024*1024);
        //已经分配到的内存大小
        long totalMemory = runtime.totalMemory()/(1024*1024);
        //所分配内存的剩余大小
        long freeMemory = runtime.freeMemory()/(1024*1024);
        //最大可用内存大小
        long usableMemory = maxMemory -totalMemory + freeMemory;

        log.info("maxMemory={}, totalMemory={}, freeMemory={}, usableMemory={}", maxMemory, totalMemory, freeMemory
        , usableMemory);

        double left = (double)usableMemory/maxMemory*100;
        DecimalFormat df  = new DecimalFormat("###.00");
        log.info("内存剩余可用：{}%",  df.format(left));
    }
}

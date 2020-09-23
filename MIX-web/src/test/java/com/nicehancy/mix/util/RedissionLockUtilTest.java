package com.nicehancy.mix.util;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.utils.RedissionLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁测试类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/9/23 11:12
 **/
@Slf4j
public class RedissionLockUtilTest extends BaseSpringTest {

    @Test
    public void test1(){

        try {
            boolean isLock = RedissionLockUtil.tryLock("TEST" + "19921577717", TimeUnit.SECONDS, 60, 60);
            if (isLock) {
                log.info("test1{}", "获取到对应的锁");
            }else {
                log.info("test1{}", "锁获取失败");
            }
            Thread.sleep(30000);
        }catch (Exception e){
            log.info("test1{}", e.getMessage());
        }finally {
            RedissionLockUtil.unlock("TEST" + "19921577717");
            log.info("test1{}", "释放对应的锁");
        }
    }

    @Test
    public void test2(){

        try {
            boolean isLock = RedissionLockUtil.tryLock("TEST" + "19921577717", TimeUnit.SECONDS, 60, 60);
            if (isLock) {
                log.info("test2{}", "获取到对应的锁");
            }else{
                log.info("test2{}", "锁获取失败");
            }
        }catch (Exception e){
            log.info("test2{}", e.getMessage());
        }finally {
            RedissionLockUtil.unlock("TEST" + "19921577717");
            log.info("test2{}", "释放对应的锁");
        }
    }
}

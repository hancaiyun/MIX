package com.nicehancy.mix.manager;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.utils.GsonUtil;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.manager.redis.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * redis测试
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/1/10 10:58
 **/
@Slf4j
public class RedisManagerTest extends BaseSpringTest {

    @Autowired
    private RedisManager redisManager;

    @Test
    public void queryTest(){

        String cache = redisManager.queryObjectByKey("19921577717");
        log.info(cache);
    }

    @Test
    public void insertTest(){
        String value = "韩旭";
        redisManager.insertObject(GsonUtil.toJson(value), "19921577717", 300);
    }

    @Test
    public void lockTest(){

        String key = "Test";
        String id = UUID.randomUUID().toString();
        long timeout = 60*3;
        MDC.put(CommonConstant.TRACE_LOG_ID, id);
        redisManager.lock(key, id, timeout);

    }

    @Test
    public void unlockTest(){

        String key = "Test";
        MDC.put(CommonConstant.TRACE_LOG_ID, UUIDUtil.createNoByUUId());
        redisManager.unlock(key);
    }
}

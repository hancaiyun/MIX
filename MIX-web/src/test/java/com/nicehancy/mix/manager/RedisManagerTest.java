package com.nicehancy.mix.manager;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.utils.GsonUtil;
import com.nicehancy.mix.manager.redis.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

        String cache = GsonUtil.fromJson(redisManager.queryObjectByKey("19921577717"), String.class);
        log.info(cache);
    }

    @Test
    public void insertTest(){
        String value = "韩旭";
        redisManager.insertObject(GsonUtil.toJson(value), "19921577717", 300);
    }
}

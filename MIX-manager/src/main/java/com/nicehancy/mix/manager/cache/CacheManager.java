package com.nicehancy.mix.manager.cache;

import com.nicehancy.mix.manager.model.CacheAddReqBO;
import com.nicehancy.mix.manager.model.CacheQueryResBO;
import com.nicehancy.mix.manager.redis.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 缓存管理manager
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 14:05
 **/
@Component
public class CacheManager {

    @Autowired
    private RedisManager redisManager;

    /**
     * 根据key查询缓存
     * @param key       key
     * @return          缓存值
     */
    public CacheQueryResBO queryCache(String key) {

        String value = redisManager.queryObjectByKey(key);
        long leftExpireTime = redisManager.queryExpireTime(key);

        CacheQueryResBO cacheQueryResBO = new CacheQueryResBO();
        cacheQueryResBO.setValue(value);
        //已失效，默认-2，将-2设置为null，标识无有效期
        cacheQueryResBO.setLeftExpireTime(leftExpireTime < 0 ? null:leftExpireTime);

        return cacheQueryResBO;
    }

    /**
     * 新增key
     * @param cacheAddReqBO 请求BO
     */
    public void addCache(CacheAddReqBO cacheAddReqBO) {

        //无有效期
        if(null == cacheAddReqBO.getExpireTime()){
            redisManager.insertObject(cacheAddReqBO.getValue(), cacheAddReqBO.getKey());
            return;
        }
        //有有效期
        redisManager.insertObject(cacheAddReqBO.getValue(), cacheAddReqBO.getKey(), cacheAddReqBO.getExpireTime());
    }

    /**
     * 缓存清理
     * @param key           key
     */
    public void deleteCache(String key) {

        redisManager.deleteObject(key);
    }
}

package com.nicehancy.mix.manager.cache;

import com.nicehancy.mix.common.utils.GsonUtil;
import com.nicehancy.mix.manager.model.CacheAddReqBO;
import com.nicehancy.mix.manager.model.CacheQueryResBO;
import com.nicehancy.mix.manager.model.CacheValueBO;
import com.nicehancy.mix.manager.redis.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

        String valueString = redisManager.queryObjectByKey(key);
        CacheValueBO cacheValueBO = new CacheValueBO();
        if(!StringUtils.isEmpty(valueString)){
            cacheValueBO = GsonUtil.fromJson(redisManager.queryObjectByKey(key), CacheValueBO.class);
        }

        long leftExpireTime = redisManager.queryExpireTime(key);

        CacheQueryResBO cacheQueryResBO = new CacheQueryResBO();
        cacheQueryResBO.setValue(cacheValueBO == null ? null:cacheValueBO.getValue());
        //已失效，默认-2，将-2设置为null，标识无有效期
        cacheQueryResBO.setLeftExpireTime(leftExpireTime < 0 ? null:leftExpireTime);

        return cacheQueryResBO;
    }

    /**
     * 新增key
     * @param cacheAddReqBO 请求BO
     */
    public void addCache(CacheAddReqBO cacheAddReqBO) {

        CacheValueBO cacheValueBO = new CacheValueBO();
        cacheValueBO.setValue(cacheAddReqBO.getValue());
        //无有效期
        if(null == cacheAddReqBO.getExpireTime()){
            redisManager.insertObject(cacheValueBO, cacheAddReqBO.getKey());
            return;
        }
        //有有效期
        redisManager.insertObject(cacheValueBO, cacheAddReqBO.getKey(), cacheAddReqBO.getExpireTime());
    }

    /**
     * 缓存清理
     * @param key           key
     */
    public void deleteCache(String key) {

        redisManager.deleteObject(key);
    }
}

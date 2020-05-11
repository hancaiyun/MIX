package com.nicehancy.mix.api;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.utils.UUIDUtil;
import com.nicehancy.mix.service.api.cache.CacheService;
import com.nicehancy.mix.service.api.model.request.cache.CacheAddReqDTO;
import com.nicehancy.mix.service.api.model.request.cache.CacheDeleteReqDTO;
import com.nicehancy.mix.service.api.model.request.cache.CacheQueryReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缓存管理接口测试类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 14:38
 **/
@Slf4j
public class CacheServiceTest extends BaseSpringTest {

    @Autowired
    private CacheService cacheService;

    @Test
    public void queryCacheTest(){

        CacheQueryReqDTO cacheQueryReqDTO = new CacheQueryReqDTO();
        cacheQueryReqDTO.setRequestSystem("MIX");
        cacheQueryReqDTO.setTraceLogId(UUIDUtil.createNoByUUId());
        cacheQueryReqDTO.setKey("19921577717");//19921577717
        cacheService.queryCache(cacheQueryReqDTO);
    }

    @Test
    public void addCacheTest(){

        CacheAddReqDTO cacheAddReqDTO = new CacheAddReqDTO();
        cacheAddReqDTO.setKey("19921577717");//19921577717
        cacheAddReqDTO.setValue("HAHA");
        //cacheAddReqDTO.setExpireTime(120L);
        cacheAddReqDTO.setTraceLogId(UUIDUtil.createNoByUUId());
        cacheAddReqDTO.setRequestSystem("MIX");
        cacheService.addCache(cacheAddReqDTO);
    }

    /**
     * 缓存清理test
     */
    @Test
    public void deleteCacheTest(){

        CacheDeleteReqDTO cacheDeleteReqDTO = new CacheDeleteReqDTO();
        cacheDeleteReqDTO.setTraceLogId(UUIDUtil.createNoByUUId());
        cacheDeleteReqDTO.setRequestSystem("MIX");
        cacheDeleteReqDTO.setKey("19921577717");
        cacheService.deleteCache(cacheDeleteReqDTO);
    }
}

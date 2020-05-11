package com.nicehancy.mix.service.api.cache;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.model.request.cache.CacheDeleteReqDTO;
import com.nicehancy.mix.service.api.model.request.cache.CacheQueryReqDTO;
import com.nicehancy.mix.service.api.model.request.cache.CacheAddReqDTO;
import com.nicehancy.mix.service.api.model.result.cache.CacheQueryResDTO;

/**
 * redis缓存管理接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 11:30
 **/
public interface CacheService {

    /**
     * 缓存查询接口
     * @param cacheQueryReqDTO      缓存查询请求DTO
     * @return                      缓存信息列表
     */
    Result<CacheQueryResDTO> queryCache(CacheQueryReqDTO cacheQueryReqDTO);

    /**
     * 缓存新增接口
     * 有即更新，没有即新增
     * @param cacheAddReqDTO     缓存更新DTO
     * @return                      更新结果
     */
    Result<Boolean> addCache(CacheAddReqDTO cacheAddReqDTO);

    /**
     * 缓存清理接口
     * @param cacheDeleteReqDTO     缓存清理请求DTO
     * @return                      清理结果
     */
    Result<Boolean> deleteCache(CacheDeleteReqDTO cacheDeleteReqDTO);
}

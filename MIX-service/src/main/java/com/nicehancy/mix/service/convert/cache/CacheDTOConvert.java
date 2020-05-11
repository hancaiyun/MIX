package com.nicehancy.mix.service.convert.cache;

import com.nicehancy.mix.manager.model.CacheAddReqBO;
import com.nicehancy.mix.manager.model.CacheQueryResBO;
import com.nicehancy.mix.service.api.model.request.cache.CacheAddReqDTO;
import com.nicehancy.mix.service.api.model.result.cache.CacheQueryResDTO;

/**
 * 缓存DTO转换类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 14:15
 **/
public class CacheDTOConvert {

    /**
     * 私有构造函数
     */
    private CacheDTOConvert(){
    }

    /**
     * 缓存查询结果BO转DTO
     * @param cacheQueryResBO               BO
     * @return                              DTO
     */
    public static CacheQueryResDTO getDTOByBO(CacheQueryResBO cacheQueryResBO) {

        if (cacheQueryResBO == null) {
            return null;
        }

        CacheQueryResDTO cacheQueryResDTO = new CacheQueryResDTO();
        cacheQueryResDTO.setValue(cacheQueryResBO.getValue());
        cacheQueryResDTO.setLeftExpireTime(cacheQueryResBO.getLeftExpireTime());

        return cacheQueryResDTO;
    }

    /**
     * 新增缓存请求DTO转BO
     * @param cacheAddReqDTO                DTO
     * @return                              BO
     */
    public static CacheAddReqBO getBOByDTO(CacheAddReqDTO cacheAddReqDTO) {

        if (cacheAddReqDTO == null) {
            return null;
        }

        CacheAddReqBO cacheAddReqBO = new CacheAddReqBO();
        cacheAddReqBO.setKey(cacheAddReqDTO.getKey());
        cacheAddReqBO.setValue(cacheAddReqDTO.getValue());
        cacheAddReqBO.setExpireTime(cacheAddReqDTO.getExpireTime());

        return cacheAddReqBO;
    }
}

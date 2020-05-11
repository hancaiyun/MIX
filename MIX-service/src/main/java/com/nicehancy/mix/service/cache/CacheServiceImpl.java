package com.nicehancy.mix.service.cache;

import com.alibaba.dubbo.config.annotation.Service;
import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.manager.cache.CacheManager;
import com.nicehancy.mix.manager.model.CacheQueryResBO;
import com.nicehancy.mix.service.api.cache.CacheService;
import com.nicehancy.mix.service.api.model.request.cache.CacheDeleteReqDTO;
import com.nicehancy.mix.service.api.model.request.cache.CacheQueryReqDTO;
import com.nicehancy.mix.service.api.model.request.cache.CacheAddReqDTO;
import com.nicehancy.mix.service.api.model.result.cache.CacheQueryResDTO;
import com.nicehancy.mix.service.convert.cache.CacheDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缓存管理接口
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 13:53
 **/
@Slf4j
@Service(timeout = 3000)
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheManager cacheManager;

    /**
     * 缓存查询
     * @param cacheQueryReqDTO      缓存查询请求DTO
     * @return                      列表结果
     */
    @Override
    public Result<CacheQueryResDTO> queryCache(CacheQueryReqDTO cacheQueryReqDTO) {

        Result<CacheQueryResDTO> result = new Result<>();
        MDC.put("TRACE_LOG_ID", cacheQueryReqDTO.getTraceLogId());
        try{
            log.info("call CacheServiceImpl queryCache param: reqDTO={}", cacheQueryReqDTO);
            //业务处理
            CacheQueryResBO cacheQueryResBO = cacheManager.queryCache(cacheQueryReqDTO.getKey());
            //设置结果
            result.setResult(CacheDTOConvert.getDTOByBO(cacheQueryResBO));
            log.info("call CacheServiceImpl queryCache result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call CacheServiceImpl queryCache failed, message：e={}， result={}", e, result);
        }
        return result;
    }

    /**
     * 新增缓存接口
     * @param cacheAddReqDTO     缓存更新DTO
     * @return
     */
    @Override
    public Result<Boolean> addCache(CacheAddReqDTO cacheAddReqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", cacheAddReqDTO.getTraceLogId());
        try{
            log.info("call CacheServiceImpl addCache param: reqDTO={}", cacheAddReqDTO);
            //业务处理
            cacheManager.addCache(CacheDTOConvert.getBOByDTO(cacheAddReqDTO));
            //设置结果
            result.setResult(true);
            log.info("call CacheServiceImpl addCache result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call CacheServiceImpl addCache failed, message：e={}， result={}", e, result);
        }
        return result;
    }

    /**
     * 缓存清理接口
     * @param cacheDeleteReqDTO     缓存清理请求DTO
     * @return                      清理结果
     */
    @Override
    public Result<Boolean> deleteCache(CacheDeleteReqDTO cacheDeleteReqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", cacheDeleteReqDTO.getTraceLogId());
        try{
            log.info("call CacheServiceImpl deleteCache param: reqDTO={}", cacheDeleteReqDTO);
            //业务处理
            cacheManager.deleteCache(cacheDeleteReqDTO.getKey());
            //设置结果
            result.setResult(true);
            log.info("call CacheServiceImpl deleteCache result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call CacheServiceImpl deleteCache failed, message：e={}， result={}", e, result);
        }
        return result;
    }
}

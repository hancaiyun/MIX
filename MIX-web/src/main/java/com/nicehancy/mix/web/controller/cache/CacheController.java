package com.nicehancy.mix.web.controller.cache;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.service.api.cache.CacheService;
import com.nicehancy.mix.service.api.model.request.cache.CacheAddReqDTO;
import com.nicehancy.mix.service.api.model.request.cache.CacheDeleteReqDTO;
import com.nicehancy.mix.service.api.model.request.cache.CacheQueryReqDTO;
import com.nicehancy.mix.service.api.model.result.cache.CacheQueryResDTO;
import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 缓存管理controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/11 16:05
 **/
@Slf4j
@Controller
@RequestMapping("/cache")
public class CacheController extends BaseController {

    @Autowired
    private CacheService cacheService;

    /**
     * 主页面
     * @return      主页视图
     */
    @RequestMapping("/page")
    public ModelAndView mainPage(){
        return new ModelAndView("cache/cache");
    }

    /**
     * 查询缓存
     * @param request   请求参数
     * @return          返回结果
     */
    @RequestMapping("/queryCache")
    @ResponseBody
    public ModelMap queryCache(HttpServletRequest request){

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        CacheQueryReqDTO reqDTO = new CacheQueryReqDTO();
        reqDTO.setTraceLogId(traceLogId);
        reqDTO.setRequestSystem(CommonConstant.SYSTEM);
        reqDTO.setKey(this.getParameters(request).get("key"));

        log.info("CacheController queryCache request PARAM: reqDTO={}", reqDTO);
        Result<CacheQueryResDTO> result = cacheService.queryCache(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result.getResult());
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("CacheController queryCache result modelMap={}", modelMap);
        return modelMap;
    }

    /**
     * 新增缓存
     * @param request   请求参数
     * @return          返回结果
     */
    @RequestMapping("/addCache")
    @ResponseBody
    public ModelMap addCache(HttpServletRequest request){

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        CacheAddReqDTO reqDTO = new CacheAddReqDTO();
        reqDTO.setTraceLogId(traceLogId);
        reqDTO.setRequestSystem(CommonConstant.SYSTEM);
        reqDTO.setKey(this.getParameters(request).get("key"));
        reqDTO.setValue(this.getParameters(request).get("value"));
        String expire = this.getParameters(request).get("expire");
        if(!StringUtils.isEmpty(expire)){
            reqDTO.setExpireTime(Long.valueOf(expire));
        }

        log.info("CacheController addCache request PARAM: reqDTO={}", reqDTO);
        Result<Boolean> result = cacheService.addCache(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result.getResult());
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("CacheController addCache result modelMap={}", modelMap);
        return modelMap;
    }

    /**
     * 删除缓存
     * @param request   请求参数
     * @return          返回结果
     */
    @RequestMapping("/deleteCache")
    @ResponseBody
    public ModelMap deleteCache(HttpServletRequest request){

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        CacheDeleteReqDTO reqDTO = new CacheDeleteReqDTO();
        reqDTO.setTraceLogId(traceLogId);
        reqDTO.setRequestSystem(CommonConstant.SYSTEM);
        reqDTO.setKey(this.getParameters(request).get("key"));

        log.info("CacheController deleteCache request PARAM: reqDTO={}", reqDTO);
        Result<Boolean> result = cacheService.deleteCache(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result.getResult());
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("CacheController deleteCache result modelMap={}", modelMap);
        return modelMap;
    }
}

package com.nicehancy.mix.service;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.manager.SuggestManager;
import com.nicehancy.mix.service.api.SuggestService;
import com.nicehancy.mix.service.api.model.request.SuggestCommitReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 意见反馈接口实现类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/13 17:08
 **/
@Slf4j
@Service
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    private SuggestManager suggestManager;
    @Override
    public Result<Boolean> commit(SuggestCommitReqDTO reqDTO) {

        Result<Boolean> result = new Result<>();
        MDC.put("TRACE_LOG_ID", reqDTO.getTraceLogId());
        try{
            log.info("call SuggestServiceImpl commit param: reqDTO={}", reqDTO);
            //业务处理
            suggestManager.commit(reqDTO.getUserNo(), reqDTO.getSuggestion());
            //设置结果
            result.setResult(true);
            log.info("call SuggestServiceImpl commit result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call SuggestServiceImpl commit failed, message：e={}， result={}", e, result);
        }
        return result;
    }
}

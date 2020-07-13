package com.nicehancy.mix.web.controller;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.service.api.SuggestService;
import com.nicehancy.mix.service.api.model.request.SuggestCommitReqDTO;
import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 意见反馈controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/7/10 11:25
 **/
@Slf4j
@Controller
public class SuggestController extends BaseController {

    @Autowired
    private SuggestService sendVercode;

    /**
     * 建议提交
     * @return  提交结果
     */
    @RequestMapping("/suggest/commit")
    @ResponseBody
    public ModelMap commit(HttpServletRequest request){

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        SuggestCommitReqDTO reqDTO = new SuggestCommitReqDTO();
        reqDTO.setUserNo(this.getParameters(request).get("userNo"));
        reqDTO.setSuggestion(this.getParameters(request).get("content"));
        reqDTO.setRequestSystem("MIX");
        reqDTO.setTraceLogId(traceLogId);

        log.info("SuggestController commit request PARAM: reqDTO={}", reqDTO);
        Result<Boolean> result = sendVercode.commit(reqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            if(result.getResult()) {
                modelMap = this.processSuccessJSON(result.getResult());
            }else {
                modelMap = this.processSuccessJSON("反馈信息提交失败");
            }
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("SuggestController commit modelMap={}", modelMap);
        return modelMap;
    }
}

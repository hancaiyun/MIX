package com.nicehancy.mix.web.message;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.enums.SendResultEnum;
import com.nicehancy.mix.common.utils.DateUtil;
import com.nicehancy.mix.service.api.message.MessageService;
import com.nicehancy.mix.service.api.model.request.message.MessageSendRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.request.message.MessageSendReqDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.model.result.message.MessageSendRecordDTO;
import com.nicehancy.mix.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 消息处理controller
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 9:57
 **/
@Slf4j
@Controller
public class MessageSendController extends BaseController {

    @Autowired
    private MessageService messageService;


    /**
     * 消息列表页
     * @return      列表页视图
     */
    @RequestMapping("/message/sendRecordpage")
    public ModelAndView recordPage(){
        return new ModelAndView("message/messagelist");
    }

    /**
     * 消息补发页
     * @return      列表页视图
     */
    @RequestMapping("/message/sendPage")
    public ModelAndView sendPage(){
        return new ModelAndView("message/send");
    }

    /**
     * 分页查询
     * @return      分页结果
     */
    @RequestMapping("/message/pageQuery")
    @ResponseBody
    public ModelMap pageQuery(HttpServletRequest request) {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        MessageSendRecordPageQueryReqDTO pageQueryReqDTO = new MessageSendRecordPageQueryReqDTO();
        pageQueryReqDTO.setTraceLogId(traceLogId);
        pageQueryReqDTO.setRequestSystem(CommonConstant.SYSTEM);
        pageQueryReqDTO.setCurrentPage(Integer.valueOf(this.getParameters(request).get("page")));
        pageQueryReqDTO.setPageSize(Integer.valueOf(this.getParameters(request).get("limit")));
        pageQueryReqDTO.setMessageType(this.getParameters(request).get("messageType"));
        pageQueryReqDTO.setRecipient(this.getParameters(request).get("recipient"));
        String startDate = this.getParameters(request).get("startDate");
        String endDate = this.getParameters(request).get("endDate");
        pageQueryReqDTO.setStartDate(StringUtils.isEmpty(startDate) ? null : DateUtil.parse(startDate, "yyyy-MM-dd"));
        pageQueryReqDTO.setEndDate(StringUtils.isEmpty(endDate) ? null : DateUtil.parse(endDate, "yyyy-MM-dd"));

        log.info("MessageSendController pageQuery request PARAM: reqDTO={}", pageQueryReqDTO);
        Result<BasePageQueryResDTO<MessageSendRecordDTO>> result = messageService.pageQuery(pageQueryReqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            if(!CollectionUtils.isEmpty(result.getResult().getPageResult())) {
                for (MessageSendRecordDTO messageSendRecordDTO : result.getResult().getPageResult()) {
                    messageSendRecordDTO.setSendResult(SendResultEnum.SUCCESS.getCode().equals(messageSendRecordDTO.
                            getSendResult()) ? SendResultEnum.SUCCESS.getDesc() : SendResultEnum.FAILURE.getDesc());
                }
                modelMap = this.processSuccessJSON(result.getResult().getPageResult(), result.getResult().getCount());
            } else {
                modelMap = this.processSuccessJSON("查无数据");
            }
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("MessageSendController pageQuery result modelMap={}", modelMap);
        return modelMap;
    }


    /**
     * 消息补发
     * @return      发送结果
     */
    @RequestMapping("/message/reSend")
    @ResponseBody
    public ModelMap reSend(HttpServletRequest request) {

        String traceLogId = UUID.randomUUID().toString();
        MDC.put("TRACE_LOG_ID", traceLogId);
        MessageSendReqDTO messageSendReqDTO = new MessageSendReqDTO();
        messageSendReqDTO.setTraceLogId(traceLogId);
        messageSendReqDTO.setRequestSystem(CommonConstant.SYSTEM);
        messageSendReqDTO.setBusinessType(this.getParameters(request).get("businessType"));
        messageSendReqDTO.setReason(this.getParameters(request).get("content"));
        messageSendReqDTO.setSender(this.getParameters(request).get("sender"));
        messageSendReqDTO.setRecipient(this.getParameters(request).get("recipient"));

        log.info("MessageSendController reSend request PARAM: reqDTO={}", messageSendReqDTO);
        Result<Boolean> result = messageService.send(messageSendReqDTO);
        ModelMap modelMap;
        if(result.isSuccess()){
            modelMap = this.processSuccessJSON(result.getResult());
        }else{
            modelMap = this.processSuccessJSON(result.getErrorMsg());
        }
        log.info("MessageSendController reSend result modelMap={}", modelMap);
        return modelMap;
    }
}

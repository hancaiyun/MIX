package com.nicehancy.mix.service.message;

import com.nicehancy.mix.common.Result;
import com.nicehancy.mix.manager.message.MessageSendRecordManager;
import com.nicehancy.mix.manager.model.MessageSendRecordBO;
import com.nicehancy.mix.service.api.message.MessageService;
import com.nicehancy.mix.service.api.model.request.message.MessageSendRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.request.message.MessageSendReqDTO;
import com.nicehancy.mix.service.api.model.result.base.BasePageQueryResDTO;
import com.nicehancy.mix.service.api.model.result.message.MessageSendRecordDTO;
import com.nicehancy.mix.service.convert.message.MessageDTOConvert;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息管理
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 10:52
 **/
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageSendRecordManager messageSendRecordManager;

    /**
     * 分页查询
     * @param pageQueryReqDTO       分页请求参数
     * @return                      返回结果
     */
    @Override
    public Result<BasePageQueryResDTO<MessageSendRecordDTO>> pageQuery(MessageSendRecordPageQueryReqDTO pageQueryReqDTO) {

        Result<BasePageQueryResDTO<MessageSendRecordDTO>> result = new Result<>();
        MDC.put("TRACE_LOG_ID", pageQueryReqDTO.getTraceLogId());
        try{
            log.info("call MessageServiceImpl pageQuery param: reqDTO={}", pageQueryReqDTO);
            //业务处理
            BasePageQueryResDTO<MessageSendRecordDTO> resDTO = new BasePageQueryResDTO<>();
            //查询总条数
            int count = messageSendRecordManager.queryCount(MessageDTOConvert.getBOByDTO(pageQueryReqDTO));

            //查询结果集
            if(0 == count) {
                resDTO.setPageResult(null);
                resDTO.setCount(0);
            }else{
                List<MessageSendRecordBO> list = messageSendRecordManager.pageQuery(MessageDTOConvert.getBOByDTO(pageQueryReqDTO));
                List<MessageSendRecordDTO> dtoList = MessageDTOConvert.getDTOSByBOS(list);
                resDTO.setPageResult(dtoList);
                resDTO.setCount(count);
            }
            result.setResult(resDTO);
            log.info("call MessageServiceImpl pageQuery result: {}", result);
        }catch (Exception e){
            result.setErrorMsg(e.getMessage());
            log.error("call MessageServiceImpl pageQuery failed, message：e={}， result={}", e, result);
        }
        return result;
    }

    @Override
    public Result<Boolean> send(MessageSendReqDTO messageSendReqDTO) {
        return null;
    }
}

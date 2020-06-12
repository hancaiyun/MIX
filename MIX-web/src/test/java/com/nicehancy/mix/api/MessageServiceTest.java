package com.nicehancy.mix.api;

import com.nicehancy.mix.base.BaseSpringTest;
import com.nicehancy.mix.common.constant.CommonConstant;
import com.nicehancy.mix.common.enums.BusinessTypeEnum;
import com.nicehancy.mix.service.api.message.MessageService;
import com.nicehancy.mix.service.api.model.request.message.MessageSendRecordPageQueryReqDTO;
import com.nicehancy.mix.service.api.model.request.message.MessageSendReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * 消息管理接口测试类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/5/9 11:22
 **/
@Slf4j
public class MessageServiceTest extends BaseSpringTest {

    @Autowired
    private MessageService messageService;

    /**
     * 发送记录分页查询测试
     */
    @Test
    public void pageQueryTest(){

        MessageSendRecordPageQueryReqDTO recordPageQueryReqDTO = new MessageSendRecordPageQueryReqDTO();
        recordPageQueryReqDTO.setCurrentPage(1);
        recordPageQueryReqDTO.setPageSize(10);
        recordPageQueryReqDTO.setTraceLogId(UUID.randomUUID().toString());
        recordPageQueryReqDTO.setRequestSystem(CommonConstant.SYSTEM);
        messageService.pageQuery(recordPageQueryReqDTO);
    }

    /**
     * 发送测试
     */
    @Test
    public void sendTest(){

        MessageSendReqDTO messageSendReqDTO = new MessageSendReqDTO();
        messageSendReqDTO.setSender("MIX");
        messageSendReqDTO.setReason("");
        messageSendReqDTO.setRequestSystem("MIX");
        messageSendReqDTO.setTraceLogId(UUID.randomUUID().toString());
        messageSendReqDTO.setRecipient("19921577717");
        messageSendReqDTO.setBusinessType(BusinessTypeEnum.USER_REGISTER.getCode());
        messageService.send(messageSendReqDTO);
    }
}
